package services.remote.event.pojo;

public class SubscriptionParams {
	private String topic;
	private String filter;

	public SubscriptionParams() {
	}

	public SubscriptionParams(String topic, String filter) {
		this.topic = topic;
		this.filter = filter;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String toString() {
		return this.topic + "->" + this.filter;
	}
}