package services.remote.event.util.tool;

import org.osgi.service.event.Event;

public abstract interface PersistentEventAdmin {
	public static final String PERSISTENT_EVENT = "com.prosyst.mbs.services.remote.event.persistent";

	public abstract Event[] getPersistentEvents(String[] paramArrayOfString,
			String paramString) throws PersistentEventsException;

	public abstract void deleteEvent(Event paramEvent);

	public abstract void deleteEvents(String[] paramArrayOfString,
			String paramString);
}