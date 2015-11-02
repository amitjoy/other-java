package com.amitinside.guava;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import static com.google.common.base.Preconditions.*;

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
		checkArgument("AMIT".length() == 3, "HHH");
	}

}
