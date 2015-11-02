package services.remote.event.util.tool;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class RemoteEventsUtil {
	private static String ID_PREFIX = Long.toString(System.currentTimeMillis(),
			24) + ".";
	private static int idCounter = 0;

	private static boolean print = true;

	public static synchronized String genSubscriptionId(String subscriptionType) {
		return ID_PREFIX + subscriptionType + "." + idCounter++;
	}

	public static services.remote.event.pojo.Event makeExportable(
			org.osgi.service.event.Event event, int sequenceNumber) {
		String topic = event.getTopic();
		String[] propNames = event.getPropertyNames();
		HashMap props = new HashMap(propNames.length, 2.0F);
		for (int i = 0; i < propNames.length; i++) {
			Object value = event.getProperty(propNames[i]);
			if (!isJsonSerializable(value, new Vector())) {
				dump("WARNING!! Skipping event property: " + propNames[i],
						", since it brings non-json-serializable value: "
								+ value);
			} else {
				props.put(propNames[i], value);
			}
		}
		props.put("com.prosyst.mbs.services.remote.event.sequence.number",
				new Integer(sequenceNumber));
		dump("FINAL DICTIONARY: ", props);
		return new services.remote.event.pojo.Event(topic, props);
	}

	public static boolean isJsonSerializable(Object o,
			Vector circularDependecies) {
		dump("Checking if isJsonSerializable: ", o, " class: ", o.getClass());

		if ((o == null) || (JSONObject.NULL.equals(o))) {
			dump("checkpoint 1. null objects are json serializable.", "");
			return true;
		}

		Class toType = getPrimitiveWrapperClass(o.getClass());

		if (((o instanceof JSONObject)) || ((o instanceof JSONArray))
				|| ((o instanceof JSONString)) || ((o instanceof Byte))
				|| ((o instanceof Character)) || ((o instanceof Short))
				|| ((o instanceof Integer)) || ((o instanceof Long))
				|| ((o instanceof Boolean)) || ((o instanceof Float))
				|| ((o instanceof Double)) || ((o instanceof String))) {
			dump("checkpoint 2. primitives and strings are json serializable.",
					"");
			return true;
		}

		if (indexOf(o, circularDependecies) != -1) {
			dump("checkpoint 3, circular dependecies! currently accumulated deps: ",
					circularDependecies);
			return false;
		}
		circularDependecies.add(o);
		Map d;
		Iterator i;
		if ((o instanceof Map)) {
			d = (Map) o;
			for (i = d.keySet().iterator(); i.hasNext();) {
				Object _key = i.next();
				if (!isJsonSerializable(d.get(_key), circularDependecies)) {
					dump("checkpoint 4. not serializable: ", d.get(_key));
					return false;
				}
			}
		} else {
			Dictionary dict;
			Enumeration e;
			if ((o instanceof Dictionary)) {
				dict = (Dictionary) o;
				for (e = dict.keys(); e.hasMoreElements();) {
					Object _key = e.nextElement();
					if (!isJsonSerializable(dict.get(_key), circularDependecies)) {
						dump("checkpoint 5. not serializable: ", dict.get(_key));
						return false;
					}
				}
			} else {
				Iterator j;
				if ((o instanceof Collection)) {
					Collection col = (Collection) o;
					for (j = col.iterator(); j.hasNext();) {
						Object next = j.next();
						if (!isJsonSerializable(next, circularDependecies)) {
							dump("checkpoint 6. not serializable: ", next);
							return false;
						}
					}
				} else if (toType.isArray()) {
					int length = Array.getLength(o);
					for (int k = 0; k < length; k++)
						if (!isJsonSerializable(Array.get(o, k),
								circularDependecies)) {
							dump("checkpoint 7. not serializable: ",
									Array.get(o, k));
							return false;
						}
				} else if (!isBean(o, circularDependecies)) {
					dump("checkpoint 15. object is NOT a bean:", o);
					return false;
				}
			}
		}
		removeCircularDep(o, circularDependecies);
		dump("checkpoint 16. object is json serializable:", o);
		return true;
	}

	private static void removeCircularDep(Object o, Vector circularDependecies) {
		int ind = indexOf(o, circularDependecies);
		circularDependecies.removeElementAt(ind);
	}

	private static int indexOf(Object o, Vector circularDependecies) {
		for (int i = 0; i < circularDependecies.size(); i++) {
			if (o == circularDependecies.elementAt(i)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean isBean(Object o, Vector circularDependecies) {
		dump("checkpoint 8. check if bean: ", o);
		try {
			o.getClass().getConstructor(new Class[0]);
		} catch (NoSuchMethodException nsme) {
			dump("checkpoint 9. no deafult constructor: ", o);
			return false;
		}

		Class clazz = o.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];

			if (!m.getDeclaringClass().equals(Object.class)) {
				String methodName = m.getName();
				Class[] types = m.getParameterTypes();
				String propName = null;
				if ((types == null) || (types.length == 0)) {
					if (methodName.startsWith("get"))
						propName = methodName.substring(3);
					else if (methodName.startsWith("is")) {
						propName = methodName.substring(2);
					}
				}
				if ((propName != null) && (!"Class".equals(propName))) {
					try {
						clazz.getMethod("set" + propName, new Class[0]);
					} catch (NoSuchMethodException nsme) {
						dump("checkpoint 11. no write method for bean attribute: ",
								propName);
						return false;
					}

					Object propValue = null;
					try {
						propValue = m.invoke(o, null);
					} catch (Exception ite) {
						dump("checkpoint 12. can not obtain value for bean attribute: ",
								propName);
						return false;
					}
					if (!isJsonSerializable(propValue, circularDependecies)) {
						dump("checkpoint 13. bean attribute not json-serializable: ",
								propName);
						return false;
					}
				}
			}
		}
		dump("checkpoint 14. object is bean:", o);
		return true;
	}

	public static Class getPrimitiveWrapperClass(Class type) {
		if ((type == null) || (!type.isPrimitive())) {
			return type;
		}

		if (Boolean.TYPE.equals(type))
			return Boolean.class;
		if (Byte.TYPE.equals(type))
			return Byte.class;
		if (Short.TYPE.equals(type))
			return Short.class;
		if (Character.TYPE.equals(type))
			return Character.class;
		if (Integer.TYPE.equals(type))
			return Integer.class;
		if (Long.TYPE.equals(type))
			return Long.class;
		if (Float.TYPE.equals(type))
			return Float.class;
		if (Double.TYPE.equals(type)) {
			return Double.class;
		}
		return type;
	}

	private static void dump(String s1, Object s2) {
		if (print)
			System.out.println(s1 + s2);
	}

	private static void dump(String s1, Object s2, Object s3, Object s4) {
		if (print)
			System.out.println(s1 + s2 + s3 + s4);
	}
}