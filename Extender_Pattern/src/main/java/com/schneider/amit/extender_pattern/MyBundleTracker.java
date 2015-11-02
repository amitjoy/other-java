package com.schneider.amit.extender_pattern;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.util.tracker.BundleTracker;

/**
 * @author SESA249907
 */
class MyBundleTracker extends BundleTracker {

    public MyBundleTracker(BundleContext context) {
        super(context, Bundle.ACTIVE, null);
    }

    @Override
    public Object addingBundle(Bundle bundle, BundleEvent event) {
        String className = (String) bundle.getHeaders().get("Action-Attribute");
        if (className != null) {
            Class<?> clazz;
            try {
                clazz = bundle.loadClass(className);
                try {
                    bundle.getBundleContext().registerService("com.amitinside.server.extender.service.MyService", clazz.newInstance(), null);
                    System.out.println("----------------------Service is registered------------------------");
                } catch (InstantiationException ex) {
                    Logger.getLogger(MyBundleTracker.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MyBundleTracker.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException e) {
            }
        }
        return bundle;
    }
}