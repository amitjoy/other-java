package impl.services.remote.events.push;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.osgi.framework.BundleContext;

public class ServerSocketListener implements Runnable {
	private BundleContext bc;
	private boolean active;
	private ServerSocket serverSocket;
	private List activeSockets = new ArrayList();

	private static final int MAX_OPEN_SOCKETS = 10;

	public ServerSocketListener(BundleContext bc) throws IOException {
		this.bc = bc;
		this.active = true;
		this.serverSocket = new ServerSocket(8081);
	}

	public void run() {
		while (this.active)
			try {
				System.out.println("IIIIIII");
				Socket socket = this.serverSocket.accept();
				System.out.println("SOCKET" + socket);
				WebSocket ws = new WebSocket(this, this.bc, socket);
				this.activeSockets.add(ws);
				ws.start();
				checkExeedingConnections();
			} catch (IOException e) {
				if (this.active)
					Activator.error("Error in socket accept", e);
			}
	}

	private void checkExeedingConnections() {
		if (this.activeSockets.size() > MAX_OPEN_SOCKETS) {
			WebSocket oldest = (WebSocket) this.activeSockets.remove(0);
			Activator.warning(
					"WebSocket Connections exeeded the maximum number of: "
							+ MAX_OPEN_SOCKETS
							+ ". Closing the oldest connection: " + oldest,
					null);
			oldest.cancel();
		}
	}

	public void cancel() {
		this.active = false;
		try {
			this.serverSocket.close();
		} catch (IOException _) {
		}
		List activeSockets = new ArrayList(this.activeSockets);

		for (Iterator sockets = activeSockets.iterator(); sockets.hasNext();) {
			WebSocket ws = (WebSocket) sockets.next();
			ws.cancel();
		}

		this.activeSockets.clear();
	}

	public void webSocketClosed(WebSocket webSocket) {
		this.activeSockets.remove(webSocket);
	}
}