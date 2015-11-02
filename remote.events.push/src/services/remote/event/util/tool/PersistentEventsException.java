package services.remote.event.util.tool;

public class PersistentEventsException extends Exception {
	private static final long serialVersionUID = System.currentTimeMillis();

	public PersistentEventsException(String message) {
		super(message);
	}
}