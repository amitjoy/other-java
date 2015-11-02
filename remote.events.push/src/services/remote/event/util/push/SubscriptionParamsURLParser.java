package services.remote.event.util.push;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.remote.event.pojo.SubscriptionParams;

public class SubscriptionParamsURLParser {
	private String topicAsAlias;
	private String[] topics;
	private String filter;
	private SubscriptionParams[] result;
	private static boolean print = Boolean
			.getBoolean("com.qivicon.remote.events.util.print");

	public SubscriptionParamsURLParser(String url) throws IOException {
		if ((url.startsWith("[")) || (url.startsWith("{"))) {
			parseJsonFormatParams(url);
			return;
		}

		int ind = url.indexOf('?');
		if (ind == -1) {
			this.topicAsAlias = trimSlashes(url);
			return;
		}

		if (ind > 1) {
			this.topicAsAlias = trimSlashes(url.substring(0, ind));
		}

		url = url.substring(ind + 1);

		if (url.startsWith("topics=")) {
			url = url.substring("topics=".length() + 1);
			parseTopicAndFilter(url);
		} else if (url.startsWith("topic=")) {
			url = url.substring("topic=".length() + 1);
			parseTopicAndFilter(url);
		} else if (url.startsWith("filter=")) {
			url = url.substring("fiter=".length() + 1);
			parseFilterAndTopic(url);
		} else {
			throw new IOException(
					"Invalid url parameters. Expected parameters: 'topics=' or 'topics=' or 'filter='");
		}
	}

	private void parseTopicAndFilter(String url) {
		String filterString = null;
		int ind = url.indexOf("&filter=");
		String topicsString;
		if (ind == -1) {
			topicsString = url;
		} else {
			topicsString = url.substring(0, ind);
			filterString = url.substring(ind + "&filter=".length());
		}

		this.filter = filterString;
		parseTopics(topicsString);
	}

	private void parseFilterAndTopic(String url) {
		String topicsString = null;

		int ind = url.indexOf("&topic=");
		int pLen = "&topic=".length();
		if (ind == -1) {
			ind = url.indexOf("&topics=");
			pLen = "&topics=".length();
		}
		String filterString;
		if (ind == -1) {
			filterString = url;
		} else {
			filterString = url.substring(0, ind);
			topicsString = url.substring(ind + pLen);
		}

		this.filter = filterString;
		if (topicsString != null)
			parseTopics(topicsString);
	}

	private void parseTopics(String topicsString) {
		if (topicsString.charAt(0) == '[') {
			topicsString = topicsString.substring(1);
		}
		if (topicsString.charAt(topicsString.length() - 1) == ']') {
			topicsString = topicsString.substring(0, topicsString.length() - 1);
		}

		StringTokenizer parser = new StringTokenizer(topicsString, ",");
		Vector accum = new Vector();
		while (parser.hasMoreTokens()) {
			accum.add(parser.nextToken().trim());
		}

		this.topics = new String[accum.size()];
		accum.copyInto(this.topics);
	}

	public SubscriptionParams[] getSubscriptionParams() throws IOException {
		if (this.result == null) {
			String[] tps = getTopics();
			this.result = new SubscriptionParams[tps.length];
			for (int i = 0; i < tps.length; i++) {
				this.result[i] = new SubscriptionParams(tps[i], getFilter());
			}
		}

		return this.result;
	}

	private String[] getTopics() throws IOException {
		if (this.topics != null) {
			return this.topics;
		}
		if (this.topicAsAlias == null) {
			throw new IOException(
					"Event topic(s) not specified in web sucket url. Can not handle the event subscription");
		}
		this.topics = new String[] { this.topicAsAlias };
		return this.topics;
	}

	private String getFilter() {
		return this.filter;
	}

	private static String trimSlashes(String s) {
		while (s.startsWith("/")) {
			s = s.substring(1);
		}
		while (s.endsWith("/")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	private void parseJsonFormatParams(String url) throws IOException {
		try {
			JSONArray arr = null;
			if (url.startsWith("["))
				arr = new JSONArray(url);
			else {
				arr = new JSONArray().put(new JSONObject(url));
			}

			this.result = new SubscriptionParams[arr.length()];
			for (int i = 0; i < this.result.length; i++) {
				JSONObject obj = arr.getJSONObject(i);
				String t = obj.getString("topic");
				String f = null;
				if (obj.has("filter")) {
					f = obj.getString("filter");
				}
				this.result[i] = new SubscriptionParams(t, f);
			}
		} catch (JSONException je) {
			dump("Failed to parse json format subscription parameters.", je);
			throw new IOException(je.toString());
		}
	}

	private static void dump(String msg, Exception e) {
		if (print) {
			System.out.println(msg);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String url = "?topics=[TEST_TOPIC_1,TEST_TOPIC_2]&filter=(|(SerialNumber=TEST_TOPIC_1_0)(SerialNumber=TEST_TOPIC_2_1))";

		SubscriptionParamsURLParser p = new SubscriptionParamsURLParser(url);
		SubscriptionParams[] params = p.getSubscriptionParams();
		for (int i = 0; i < params.length; i++)
			System.out.println("--> " + params[i]);
	}
}