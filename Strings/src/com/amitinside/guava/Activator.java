package com.amitinside.guava;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	public static void main(String... str) {
		byte[] bytes = null;
		bytes = "AMITANIT".getBytes(Charsets.UTF_8);
		
		System.out.println(Strings.padEnd("AMIT", 10, 'x'));
		String string = "AMIT IS A GOOD BOY";
		System.out.println(CharMatcher.WHITESPACE.replaceFrom(string, '-'));
	}

}
