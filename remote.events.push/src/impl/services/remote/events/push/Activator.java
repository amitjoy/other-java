package impl.services.remote.events.push;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	static Integer priority = null;
	private ServerSocketListener socketListener;
	public static final String ROOT_ALIAS = "/remote/events";

	public void start(BundleContext bc) throws Exception {
		priority = new Integer(1);

		info("Creating WebSocket server...");

		this.socketListener = new ServerSocketListener(bc);
		new Thread(this.socketListener, "WebSocket Listener").start();

		info("WebSocket Server is started.");
	}

	public void stop(BundleContext bc) throws Exception {
		info("Stopping WebSocket server...");
		this.socketListener.cancel();
		info("WebSocket server stopped!");
	}

	static void debug(String str) {
		System.out.println("[DEBUG] : " + str);
	}

	static void info(String str) {
		System.out.println("[INFO] : " + str);
	}

	static void warning(String str, Throwable th) {
		System.out.println("[WARNING] : " + str);
	}

	static void error(String str, Throwable th) {
		System.out.println("[ERROR] : " + str);
		if (th != null) {
			th.printStackTrace();
		}
	}

}
