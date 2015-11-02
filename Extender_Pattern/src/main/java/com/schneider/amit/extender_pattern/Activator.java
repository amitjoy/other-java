package com.schneider.amit.extender_pattern;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class Activator implements BundleActivator {

    MyBundleTracker bundleTracker;
    Class<?> clazz;

    public void start(BundleContext context) throws Exception {
        String className = context.getBundle().getHeaders().get("Action-Attribute").toString();
        clazz = context.getBundle().loadClass(className);
        bundleTracker = new MyBundleTracker(FrameworkUtil.getBundle(clazz).getBundleContext());
        bundleTracker.open();
    }

    public void stop(BundleContext context) throws Exception {
    }

}
