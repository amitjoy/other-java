package com.amitinside.guava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	public static void main(String... strings) {
		List<String> stringList = new ArrayList<>();
		stringList.add("AMIT");
		stringList.add(null);
		stringList.add("ANIT");
		
		String string = Joiner.on("-").skipNulls().join(stringList);
		string = Joiner.on(":").useForNull("NO TEXT").join(stringList);
		
		StringBuilder builder = new StringBuilder();
		builder = Joiner.on(":").skipNulls().appendTo(builder, stringList);
		
		Map<String, String> map = new HashMap<>();
		map.put("1", "AMIT");
		map.put("2", "ANIT");
		MapJoiner joiner = Joiner.on(":").withKeyValueSeparator("=");;
		
		System.out.println(joiner.join(map));
	}
}
