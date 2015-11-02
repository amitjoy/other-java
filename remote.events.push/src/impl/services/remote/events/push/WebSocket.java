package impl.services.remote.events.push;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.EventHandler;
import services.remote.event.pojo.SubscriptionParams;
import services.remote.event.util.push.SubscriptionParamsURLParser;
import services.remote.event.util.tool.JsonSerializer;
import services.remote.event.util.tool.PersistentEventAdmin;
import services.remote.event.util.tool.PersistentEventsException;
import services.remote.event.util.tool.RemoteEventsUtil;

public class WebSocket implements Runnable, EventHandler {
	private ServerSocketListener parrent;
	private BundleContext bc;
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private boolean active;
	private Map headers = new HashMap();
	private boolean legacyVersion = false;
	private byte[] key3;
	private String subscriptionId;
	private ServiceRegistration[] regs;
	private int eventSequence = 0;

	public WebSocket(ServerSocketListener parrent, BundleContext bc,
			Socket socket) throws IOException {
		this.parrent = parrent;
		this.bc = bc;
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
	}

	public void start() {
		this.active = true;
		new Thread(this, "WebSocket Thread").start();
	}

	public void cancel() {
		this.active = false;

		if (this.regs != null) {
			for (int i = 0; i < this.regs.length; i++)
				try {
					this.regs[i].unregister();
				} catch (Exception _) {
				}
			this.regs = null;
		}
		try {
			this.socket.close();
		} catch (IOException e) {
		}
		this.parrent.webSocketClosed(this);
	}

	public void run() {
		Activator.info("New websocket connection.");
		if (!this.active) {
			return;
		}

		String uri = null;
		try {
			uri = readClientHandshake();
			writeServerHandshake(uri);
		} catch (Exception e) {
			Activator.error("Malformed Web Socket Request", e);
			cancel();
			return;
		}
		try {
			registerSubscription(uri);
		} catch (Exception e) {
			Activator.error("Malformed WebSocket request", e);
			cancel();
			return;
		}
		while (true) {
			if (this.active) {
				try {
					int b = this.in.read();
					if (b == -1)
						cancel();
				} catch (IOException e) {
					Activator.debug(e.toString());
					cancel();
				}
			}
		}

	}

	private String readClientHandshake() throws IOException {
		DataInputStream reader = new DataInputStream(this.in);

		String url = processGetHeaderLine(reader.readLine());
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			line = line.trim();
			if (line.length() == 0) {
				break;
			}
			parseHandshakeHeader(line);
		}

		Activator.info("Headers: " + this.headers);

		checkMandatoryHeader("upgrade", "websocket");
		checkMandatoryHeader("connection", "upgrade");

		this.legacyVersion = ((this.headers.get("Sec-WebSocket-Key1"
				.toLowerCase()) != null) && (this.headers
				.get("Sec-WebSocket-Key2".toLowerCase()) != null));

		if (this.legacyVersion) {
			this.key3 = new byte[8];
			reader.readFully(this.key3);
		}

		return url;
	}

	private void writeServerHandshake(String uri) throws IOException {
		PrintWriter writer = new PrintWriter(this.out);
		if (this.legacyVersion)
			writer.print("HTTP/1.1 101 WebSocket Protocol Handshake\r\n");
		else {
			writer.print("HTTP/1.1 101 Switching Protocols\r\n");
		}

		writer.print("Upgrade: websocket\r\n");
		writer.print("Connection: Upgrade\r\n");

		if (this.legacyVersion) {
			String origin = (String) this.headers.get("Origin".toLowerCase());
			if (origin != null) {
				writer.print("Sec-WebSocket-Origin: " + origin + "\r\n");
			}

			String host = (String) this.headers.get("Host".toLowerCase());
			if (host != null) {
				writer.print("Sec-WebSocket-Location: ws://" + host + uri
						+ "\r\n");
			}

			String protocol = (String) this.headers
					.get("Sec-WebSocket-Protocol".toLowerCase());
			if (protocol != null) {
				writer.print("Sec-WebSocket-Protocol: " + protocol + "\r\n");
			}

			writer.print("\r\n");
		} else {
		}

		writer.flush();

		if (this.legacyVersion)
			this.out.write(computeLegacyChallengeResponse());
	}

	private byte[] computeLegacyChallengeResponse() {
		String key1 = (String) this.headers.get("Sec-WebSocket-Key1"
				.toLowerCase());
		String key2 = (String) this.headers.get("Sec-WebSocket-Key2"
				.toLowerCase());

		long keyNumber1 = toKeyNumber(key1);
		long keyNumber2 = toKeyNumber(key2);

		byte[] challenge = new byte[16];
		try {
			to32BitInteger(keyNumber1, challenge, 0);
			to32BitInteger(keyNumber2, challenge, 4);

			System.arraycopy(this.key3, 0, challenge, 8, 8);

			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(challenge);
			return m.digest();
		} catch (NoSuchAlgorithmException e) {
			Activator.error(e.toString(), e);
		}
		return challenge;
	}

	private long toKeyNumber(String key) {
		String numbers = "";
		int numberOfSpaces = 0;

		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);

			if (Character.isDigit(c))
				numbers = numbers + c;
			else if (c == ' ') {
				numberOfSpaces++;
			}
		}

		return Long.parseLong(numbers) / numberOfSpaces;
	}

	private void to32BitInteger(long key, byte[] dest, int pos) {
		dest[pos] = ((byte) (int) (key >>> 24 & 0xFF));
		dest[(pos + 1)] = ((byte) (int) (key >>> 16 & 0xFF));
		dest[(pos + 2)] = ((byte) (int) (key >>> 8 & 0xFF));
		dest[(pos + 3)] = ((byte) (int) (key & 0xFF));
	}

	private String processGetHeaderLine(String line) throws IOException {
		Activator.info("Request header - " + line);
		if ((line == null) || (line.trim().length() == 0)) {
			throw new IOException("Empty HTTP request header: " + line);
		}

		if ((!line.startsWith("GET ")) || (!line.endsWith(" HTTP/1.1"))) {
			throw new IOException("Invalid HTTP request header: " + line);
		}

		return line.substring(4, line.length() - 9);
	}

	private void parseHandshakeHeader(String line) throws IOException {
		Activator.info("Request header - " + line);

		int index = line.indexOf(':');
		if (index <= 0) {
			throw new IOException("Invalid HTTP header: " + line);
		}

		this.headers.put(line.substring(0, index).trim().toLowerCase(), line
				.substring(index + 1).trim());
	}

	private void checkMandatoryHeader(String key, String value)
			throws IOException {
		String headerValue = (String) this.headers.get(key);
		if (headerValue == null) {
			throw new IOException("Missing mandatory WebSocket header: " + key);
		}

		if ((value != null) && (headerValue.toLowerCase().indexOf(value) == -1))
			throw new IOException("The value of mandatory WebSocket header '"
					+ key + "' must be " + value);
	}

	private void registerSubscription(String uri) throws IOException {
		uri = URLDecoder.decode(uri, "UTF-8");

		int ind = uri.indexOf("/remote/events");
		if (ind == -1) {
			throw new IOException("Unexpected websocket uri: " + uri
					+ ". Can not handle the event subscription");
		}

		uri = uri.substring(ind + "/remote/events".length());
		while (uri.charAt(0) == '/') {
			uri = uri.substring(1);
		}

		if (uri.length() == 0) {
			throw new IOException("No event topic specified in uri: " + uri
					+ ". Can not handle the event subscription");
		}

		SubscriptionParams[] subscrParams = new SubscriptionParamsURLParser(uri)
				.getSubscriptionParams();

		this.subscriptionId = RemoteEventsUtil.genSubscriptionId("push");
		fillPersistentEvents(subscrParams);

		this.regs = new ServiceRegistration[subscrParams.length];
		for (int i = 0; i < subscrParams.length; i++) {
			Hashtable regProps = new Hashtable(4, 2.0F);
			regProps.put("subscription-id", this.subscriptionId);
			regProps.put("event.topics", subscrParams[i].getTopic());
			String filter = subscrParams[i].getFilter();
			if ((filter != null) && (!"null".equalsIgnoreCase(filter))) {
				regProps.put("event.filter", filter);
			}
			regProps.put("mbs.events.handlerPriority", Activator.priority);
			Activator.debug("registration: " + regProps);
			this.regs[i] = this.bc.registerService(
					EventHandler.class.getName(), this, regProps);
		}
	}

	public void handleEvent(org.osgi.service.event.Event event) {
		Activator.debug("--------------> handle event !!!! " + event);
		try {
			services.remote.event.pojo.Event e = RemoteEventsUtil
					.makeExportable(event, getSequence());
			String serialized = JsonSerializer.jsonSerializeableObject(e)
					.toString();
			Activator.debug("--------------> serialized: " + serialized);

			if (this.legacyVersion)
				writeLegacyMessage(serialized);
			else
				writeTextMessageFrame(serialized);
		} catch (Throwable t) {
			Activator.error(
					"Failed to send an event over the websocket. Subscription id: "
							+ this.subscriptionId, t);
		}
	}

	private synchronized int getSequence() {
		return this.eventSequence++;
	}

	private void writeTextMessageFrame(String message) throws IOException {
		OutputStream out = new BufferedOutputStream(this.out);

		out.write(129);

		byte[] utfMessage = message.getBytes("UTF-8");
		int length = utfMessage.length;

		if (length <= 125) {
			out.write(length);
		} else if (length <= 65535) {
			out.write(126);
			out.write(length >>> 8 & 0xFF);
			out.write(length & 0xFF);
		} else {
			out.write(127);
			new DataOutputStream(out).writeLong(length);
		}

		out.write(utfMessage);
		out.flush();
	}

	private void writeLegacyMessage(String message) throws IOException {
		OutputStream out = new BufferedOutputStream(this.out);

		out.write(0);
		out.write(message.getBytes("UTF-8"));
		out.write(255);
		out.flush();
	}

	private void fillPersistentEvents(SubscriptionParams[] subscrParams) {
		Activator.info("Fill persistent events for: " + toString());
		ServiceReference ref = this.bc
				.getServiceReference(PersistentEventAdmin.class.getName());
		if (ref == null) {
			Activator.warning(
					"PersistentEventAdmin is not available. Will not send persistent events for: "
							+ this.subscriptionId, null);
			return;
		}
		PersistentEventAdmin peAdmin = (PersistentEventAdmin) this.bc
				.getService(ref);
		if (peAdmin == null) {
			Activator
					.warning(
							"PersistentEventAdmin is not available. Will not send persistent events for subscription: "
									+ this.subscriptionId, null);
			return;
		}
		try {
			for (int k = 0; k < subscrParams.length; k++) {
				String[] eventTopics = { subscrParams[k].getTopic() };
				String eventFilter = subscrParams[k].getFilter();
				org.osgi.service.event.Event[] pEvents = peAdmin
						.getPersistentEvents(eventTopics, eventFilter);
				if ((pEvents != null) && (pEvents.length != 0)) {
					Activator.info("Fill persistent events pEvents: "
							+ pEvents.length);
					org.osgi.service.event.Event[] result = new org.osgi.service.event.Event[pEvents.length];
					for (int i = 0; i < result.length; i++) {
						Activator.info("Fill persistent event: " + pEvents[i]);
						handleEvent(pEvents[i]);
					}
				}
			}
		} catch (PersistentEventsException pee) {
			Activator.warning("Failed to retrieve persistent events for: "
					+ this.subscriptionId, pee);
		}

	}
}