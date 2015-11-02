package services.remote.event.pojo;

import java.util.HashMap;

public class Event {
	//public static final String EVENT_SEQUENCE_PROP = "com.prosyst.mbs.services.remote.event.sequence.number";
	private String topic;
	private HashMap properties;

	public Event() {
	}

	public Event(String topic, HashMap properties) {
		this.topic = topic;
		this.properties = properties;
	}

	public String gettopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public HashMap getproperties() {
		return this.properties;
	}

	public void setProperties(HashMap properties) {
		this.properties = properties;
	}

	public String toString() {
		return "<Event topic:" + this.topic + ", props:" + this.properties
				+ ">";
	}
}