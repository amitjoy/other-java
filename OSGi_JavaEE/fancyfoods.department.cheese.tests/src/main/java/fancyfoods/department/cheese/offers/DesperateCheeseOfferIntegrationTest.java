package fancyfoods.department.cheese.offers;

import javax.sql.XADataSource;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import fancyfoods.food.Inventory;
import fancyfoods.offers.SpecialOffer;

import static org.junit.Assert.*;


public class DesperateCheeseOfferIntegrationTest {

		protected BundleContext ctx = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	protected <T> T waitForService(Bundle b, Class<T> clazz) {
		try {
			BundleContext bc = b.getBundleContext();
			ServiceTracker st = new ServiceTracker(bc, clazz.getName(), null);
			st.open();
			Object service = st.waitForService(30 * 1000);
			assertNotNull("No service of the type " + clazz.getName()
					+ " was registered.", service);
			st.close();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return (T) service;
		} catch (Exception e) {
			fail("failed to register services for " + b.getSymbolicName()
					+ e.getMessage());
			return null;
		}
	}

	@Test
	public void testOfferReturnsCorrectFood() {

		Bundle bundle = getInstalledBundle(ctx, "fancyfoods.department.cheese");
		try {
			bundle.start();
		} catch (BundleException e) {
			fail(e.toString());
		}
		assertNotNull("The cheese bundle is not installed", bundle);
		assertEquals(Bundle.ACTIVE, bundle.getState());

		SpecialOffer offer = waitForService(bundle, SpecialOffer.class);


		assertNotNull("The special offer gave a null food.",
				offer.getOfferFood());

		assertEquals("Did not expect " + offer.getOfferFood().getName(),
				"Wensleydale cheese", offer.getOfferFood().getName());
	}

	@Test
	public void testInventoryPresent() {

		Bundle bundle = getInstalledBundle(ctx, "fancyfoods.persistence");
		assertNotNull("The persistence bundle is not installed", bundle);
		//	assertEquals(Bundle.ACTIVE, bundle.getState());
		try {
			bundle.start();
		} catch (BundleException e) {
			fail(e.toString());
		}

		Inventory inv = waitForService(bundle, Inventory.class);
		assertNotNull(inv);
		inv.createFood("hi", 3.99, 4);

	}

	@Test
	public void testDatasourcePresent() {

		Bundle bundle = getInstalledBundle(ctx, "fancyfoods.datasource");
		assertNotNull("Datasource bundle is not installed", bundle);
		try {
			bundle.start();
		} catch (BundleException e) {
			fail(e.toString());
		}

		XADataSource inv = waitForService(bundle, XADataSource.class);
		assertNotNull(inv);
	}

	@Test
	public void testBundleIsActive() {

		Bundle bundle = getInstalledBundle(ctx, "fancyfoods.department.cheese");
		assertNotNull("Cheese bundle is not installed", bundle);
		assertEquals(Bundle.ACTIVE, bundle.getState());
	}

	protected Bundle getInstalledBundle(BundleContext ctx, String symbolicName) {
		assertNotNull("No bundle context was injected.", ctx);
		for (Bundle b : ctx.getBundles()) {
			if (b.getSymbolicName().equals(symbolicName)) {
				return b;
			}
		}
		return null;
	}

	@Test
	public void testOpenjpaPresent() {

		Bundle bundle = getInstalledBundle(ctx, "org.apache.openjpa");
		assertNotNull("Open JPA is not installed", bundle);
		try {
			bundle.start();
		} catch (BundleException e) {
			fail(e.toString());
		}
		assertEquals(Bundle.ACTIVE, bundle.getState());

	}
}
