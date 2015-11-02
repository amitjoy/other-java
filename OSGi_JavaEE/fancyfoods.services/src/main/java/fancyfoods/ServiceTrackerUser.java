package fancyfoods;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import fancyfoods.offers.SpecialOffer;

public class ServiceTrackerUser {

	private static final long TIMEOUT = 0;

	public SpecialOffer ug(BundleContext ctx) {
		ServiceTracker<SpecialOffer, SpecialOffer> tracker = new ServiceTracker<SpecialOffer, SpecialOffer>(
				ctx, SpecialOffer.class.getName(), null);

		tracker.open();
		try {
			
			SpecialOffer offer = (SpecialOffer) tracker.waitForService(TIMEOUT);
			
			if (offer == null) {
				throw new RuntimeException(
						"The SpecialOffer service is not available.");
			}
			tracker.close();
			return offer;
		} catch (InterruptedException e) {
			return null;
		}
	}

	public SpecialOffer ug2(BundleContext ctx) {
		
		String name = SpecialOffer.class.getName();
		ServiceTracker<SpecialOffer, SpecialOffer> tracker;
		tracker = new ServiceTracker<SpecialOffer, SpecialOffer>(
				ctx, name, null);
		tracker.open();
		SpecialOffer offer = (SpecialOffer) tracker.getService();
		if (offer == null) {
			throw new RuntimeException(
					"The SpecialOffer service is not available.");
		}
		tracker.close();
		
		return offer;
	}
}
