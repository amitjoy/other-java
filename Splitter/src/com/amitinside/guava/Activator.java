package com.amitinside.guava;

import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
import com.google.common.collect.Maps;

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
		String str = "AMIT:ANIT:null";
		Iterable<String> list = Splitter.on(":").split(str);
		for(String s: list) {
			System.out.println(s);
		}
		
		Map<String, String> map = Maps.newHashMap();
		str = "1=AMIT:2=ANIT";
		MapSplitter splitter = Splitter.on(":").withKeyValueSeparator("=");
		map = splitter.split(str);
		System.out.println(map);
	}

}
