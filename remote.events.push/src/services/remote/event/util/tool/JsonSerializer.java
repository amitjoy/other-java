package services.remote.event.util.tool;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class JsonSerializer {
	public static final JSONObject toJSON(JSONObject dest, String key,
			Object object) throws JSONException, IllegalAccessException,
			InvocationTargetException {
		return dest.put(key, jsonSerializeableObject(object));
	}

	public static final Object jsonSerializeableObject(Object o)
			throws IllegalAccessException, InvocationTargetException,
			JSONException {
		if ((o == null) || (JSONObject.NULL.equals(o)))
			return null;

		Class toType = getPrimitiveWrapperClass(o.getClass());

		if (((o instanceof JSONObject)) || ((o instanceof JSONArray))
				|| ((o instanceof JSONString)) || ((o instanceof Byte))
				|| ((o instanceof Character)) || ((o instanceof Short))
				|| ((o instanceof Integer)) || ((o instanceof Long))
				|| ((o instanceof Boolean)) || ((o instanceof Float))
				|| ((o instanceof Double)) || ((o instanceof String))) {
			return o;
		}
		if ((o instanceof Map)) {
			Map d = (Map) o;
			JSONObject json = new JSONObject();
			for (Iterator i = d.keySet().iterator(); i.hasNext();) {
				Object _key = i.next();
				json.put(_key.toString(), jsonSerializeableObject(d.get(_key)));
			}
			return json;
		}
		if ((o instanceof Dictionary)) {
			Dictionary d = (Dictionary) o;
			JSONObject json = new JSONObject();
			for (Enumeration e = d.keys(); e.hasMoreElements();) {
				Object _key = e.nextElement();
				json.put(_key.toString(), jsonSerializeableObject(d.get(_key)));
			}
			return json;
		}
		if ((o instanceof Collection)) {
			Collection d = (Collection) o;
			JSONArray json = new JSONArray();
			for (Iterator i = d.iterator(); i.hasNext();) {
				json.put(jsonSerializeableObject(i.next()));
			}
			return json;
		}
		if (toType.isArray()) {
			int length = Array.getLength(o);
			JSONArray json = new JSONArray();
			for (int i = 0; i < length; i++) {
				json.put(jsonSerializeableObject(Array.get(o, i)));
			}
			return json;
		}
		return beanToJSON(o);
	}

	private static final JSONObject beanToJSON(Object o)
			throws IllegalAccessException, InvocationTargetException,
			JSONException {
		Class clazz = o.getClass();
		Method[] methods = clazz.getMethods();
		JSONObject json = new JSONObject();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];

			if (!m.getDeclaringClass().equals(Object.class)) {
				String name = m.getName();
				Class[] types = m.getParameterTypes();
				if ((types == null) || (types.length == 0)) {
					if (name.startsWith("get")) {
						Method pm = getMethodFromPublicClass(m);
						if (null != pm)
							toJSON(json, name.substring(3), pm.invoke(o, null));
					} else if (name.startsWith("is")) {
						Method pm = getMethodFromPublicClass(m);
						if (null != pm)
							toJSON(json, name.substring(2), pm.invoke(o, null));
					}
				}
			}
		}
		return json;
	}

	private static final Method getMethodFromPublicClass(Method m) {
		Class c = m.getDeclaringClass();

		if (!Modifier.isPublic(c.getModifiers())) {
			Class[] ca = c.getInterfaces();
			for (int i = 0; (ca != null) && (i < ca.length); i++) {
				Method replace;
				if (null != (replace = getMethodFrom(ca[i], m)))
					return replace;
			}
			do {
				c = c.getSuperclass();
				Method replace;
				if (null != (replace = getMethodFrom(c, m)))
					return replace;
			} while (!Object.class.equals(c));
		} else {
			return m;
		}
		Method replace;
		Class[] ca;
		return null;
	}

	private static final Method getMethodFrom(Class clazz, Method m) {
		String name = m.getName();
		Class ret = m.getReturnType();
		Class[] params = m.getParameterTypes();

		Method[] methods = clazz.getMethods();

		for (int i = 0; (methods != null) && (i < methods.length); i++) {
			Method x = methods[i];
			if ((Modifier.isPublic(x.getModifiers()))
					&& (name.equals(x.getName()))
					&& (ret.equals(x.getReturnType()))
					&& (same(params, x.getParameterTypes()))) {
				return x;
			}
		}
		return null;
	}

	private static final boolean same(Class[] params1, Class[] params2) {
		if (params1.length == params2.length) {
			for (int j = 0; j < params1.length; j++) {
				if (params1[j] != params2[j])
					return false;
			}
			return true;
		}
		return false;
	}

	public static final Object[] map(boolean passAdditionalFirstParam,
			JSONArray array, Class[] types) throws JSONException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		int additionalParam = passAdditionalFirstParam ? 1 : 0;

		Object[] ret = new Object[types.length];

		for (int i = additionalParam; i < types.length; i++) {
			ret[i] = map(array.get(i - additionalParam), types[i]);
		}

		return ret;
	}

	static final Object map(Object jsonData, Class toType)
			throws JSONException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		if ((jsonData == null) || (toType == null))
			throw new NullPointerException();

		if (JSONObject.NULL.equals(jsonData))
			return null;

		if ((toType == Object.class) || (toType.equals(Object.class))) {
			if ((jsonData instanceof JSONArray))
				toType = List.class;
			else if ((jsonData instanceof JSONObject))
				toType = Map.class;
			else {
				return jsonData;
			}

		}

		if ((toType.equals(JSONObject.class))
				&& ((jsonData instanceof JSONObject))) {
			return jsonData;
		}
		if ((toType.equals(JSONArray.class))
				&& ((jsonData instanceof JSONArray))) {
			return jsonData;
		}

		toType = getPrimitiveWrapperClass(toType);

		if (toType.isArray()) {
			Class component = toType.getComponentType();
			JSONArray array = (JSONArray) jsonData;
			int length = array.length();
			Object ret = Array.newInstance(component, length);
			for (int i = 0; i < length; i++) {
				Array.set(ret, i, map(array.get(i), component));
			}
			return ret;
		}

		if ((List.class.isAssignableFrom(toType))
				|| (toType.isAssignableFrom(List.class))) {
			JSONArray array = (JSONArray) jsonData;
			int length = array.length();
			List ret = List.class.equals(toType) ? new ArrayList(length)
					: (List) toType.newInstance();

			for (int i = 0; i < length; i++) {
				Object o = array.get(i);
				if ((o instanceof JSONObject))
					o = map(o, Map.class);
				else if ((o instanceof JSONArray))
					o = map(o, List.class);
				else {
					o = map(o, Object.class);
				}
				ret.add(o);
			}
			return ret;
		}

		if ((Dictionary.class.isAssignableFrom(toType))
				|| (toType.isAssignableFrom(Dictionary.class))) {
			JSONObject json = (JSONObject) jsonData;
			Dictionary ret = Dictionary.class.equals(toType) ? new Hashtable(
					json.length()) : (Dictionary) toType.newInstance();

			for (Iterator i = json.keys(); i.hasNext();) {
				String key = (String) i.next();
				Object o = json.get(key);
				if ((o instanceof JSONObject))
					o = map(o, Dictionary.class);
				else if ((o instanceof JSONArray))
					o = map(o, List.class);
				else {
					o = map(o, Object.class);
				}
				ret.put(key, o);
			}
			return ret;
		}

		if ((Map.class.isAssignableFrom(toType))
				|| (toType.isAssignableFrom(Map.class))) {
			JSONObject json = (JSONObject) jsonData;
			Map ret = Map.class.equals(toType) ? new HashMap(json.length())
					: (Map) toType.newInstance();

			for (Iterator i = json.keys(); i.hasNext();) {
				String key = (String) i.next();
				Object o = json.get(key);
				if ((o instanceof JSONObject))
					o = map(o, Map.class);
				else if ((o instanceof JSONArray))
					o = map(o, List.class);
				else {
					o = map(o, Object.class);
				}
				ret.put(key, o);
			}
			return ret;
		}

		if (Byte.class.equals(toType)) {
			return (jsonData instanceof Byte) ? jsonData : new Byte(
					((Number) jsonData).byteValue());
		}
		if (Double.class.equals(toType)) {
			return (jsonData instanceof Double) ? jsonData : new Double(
					((Number) jsonData).doubleValue());
		}
		if (Float.class.equals(toType)) {
			return (jsonData instanceof Float) ? jsonData : new Float(
					((Number) jsonData).floatValue());
		}
		if (Integer.class.equals(toType)) {
			return (jsonData instanceof Integer) ? jsonData : new Integer(
					((Number) jsonData).intValue());
		}
		if (Long.class.equals(toType)) {
			return (jsonData instanceof Long) ? jsonData : new Long(
					((Number) jsonData).longValue());
		}
		if (Short.class.equals(toType)) {
			return (jsonData instanceof Short) ? jsonData : new Short(
					((Number) jsonData).shortValue());
		}
		if (Number.class.equals(toType))
			return (Number) jsonData;
		if (Boolean.class.equals(toType))
			return (Boolean) jsonData;
		if (String.class.equals(toType))
			return (String) jsonData;
		if (StringBuffer.class.equals(toType))
			return new StringBuffer((String) jsonData);
		if (Character.class.equals(toType)) {
			return new Character(((String) jsonData).charAt(0));
		}
		return mapToBean((JSONObject) jsonData, toType);
	}

	private static final Object mapToBean(JSONObject beanData, Class toType)
			throws InstantiationException, IllegalAccessException,
			JSONException, IllegalArgumentException, InvocationTargetException {
		Object bean = toType.newInstance();
		Method[] methods = toType.getMethods();

		for (Iterator i = beanData.keys(); i.hasNext();) {
			String key = (String) i.next();
			Object val = beanData.get(key);
			Method propSetter = findSetter("set" + capitalize(key), methods);
			if (propSetter == null)
				throw new IllegalArgumentException();

			Class[] paramTypes = propSetter.getParameterTypes();
			if ((paramTypes == null) || (paramTypes.length != 1)) {
				throw new IllegalArgumentException();
			}
			propSetter.invoke(bean, new Object[] { map(val, paramTypes[0]) });
		}

		return bean;
	}

	private static final Method findSetter(String name, Method[] methods) {
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(name))
				return methods[i];
		}
		return null;
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

	private static final String capitalize(String lower) {
		if ((lower == null) || (lower.trim().length() == 0)) {
			return null;
		}
		char[] chars = lower.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return String.valueOf(chars);
	}
}