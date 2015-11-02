package fancyfoods.department.cheese.test;

import javax.sql.XADataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.OptionUtils;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.options.MavenArtifactProvisionOption;
import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;

import fancyfoods.food.Inventory;
import fancyfoods.offers.SpecialOffer;

import static org.junit.Assert.*;

import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(JUnit4TestRunner.class)
public class DesperateCheeseOfferIntegrationTest {

    // TODO why not working?    @Inject
    //    protected BundleContext ctx;

    
    @Test
    public void testOfferReturnsCorrectFood(BundleContext ctx) {

        Bundle bundle = getInstalledBundle(ctx, 
                            "fancyfoods.department.cheese");
        try {
            bundle.start();
        } catch (BundleException e) {
            fail(e.toString());
        }
        SpecialOffer offer = waitForService(bundle, 
                                 SpecialOffer.class);
        assertNotNull("The special offer gave a null food.",
                offer.getOfferFood());
        assertEquals("Did not expect " + 
            offer.getOfferFood().getName(), "Wensleydale cheese",
            offer.getOfferFood().getName());
    }

    protected <T> T waitForService(Bundle b, Class<T> clazz) {
        try {
            BundleContext bc = b.getBundleContext();
            ServiceTracker st = 
                new ServiceTracker(bc, clazz.getName(), null);
            st.open();
            Object service = st.waitForService(30 * 1000);
            assertNotNull("No service of the type " + clazz.getName()
                    + " was registered.", service);
            st.close();
            return (T) service;
        } catch (Exception e) {
            fail("Failed to register services for " + 
                 b.getSymbolicName() + e.getMessage());
            return null;
        }
    }

    @Test
    public void testInventoryPresent(BundleContext ctx) {
        assertNotNull("WHY NO INJECTION??", ctx);

        Bundle bundle = getInstalledBundle(ctx, "fancyfoods.persistence");
        assertNotNull("The persistence bundle is not installed", bundle);
        //    assertEquals(Bundle.ACTIVE, bundle.getState());
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
    public void testDatasourcePresent(BundleContext ctx) {

        Bundle bundle = getInstalledBundle(ctx, "fancyfoods.datasource");
        assertNotNull("Datasource bundle is not installed", bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());

        XADataSource inv = waitForService(bundle, XADataSource.class);
        assertNotNull(inv);
    }

    @Test
    public void testBundleIsActive(BundleContext ctx) {

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
    public void testOpenjpaPresent(BundleContext ctx) {

        Bundle bundle = getInstalledBundle(ctx, "org.apache.openjpa");
        assertNotNull("Open JPA is not installed", bundle);
        try {
            bundle.start();
        } catch (BundleException e) {
            fail(e.toString());
        }
        assertEquals(Bundle.ACTIVE, bundle.getState());

    }

    @Test
    public void testOfferReturnsCorrectFoodWithAllDependencies(BundleContext ctx) {
    
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

    
    @Configuration
    public static Option[] configuration() {
        MavenArtifactProvisionOption foodGroup = mavenBundle().groupId(
                "fancyfoods");
        Option[] fancyFoodsBundles = options(
                foodGroup.artifactId("fancyfoods.department.cheese").
                    version("1.0.0"),
                foodGroup.artifactId("fancyfoods.api").
                    version("1.0.0"),
                foodGroup.artifactId("fancyfoods.persistence").
                    version("1.0.0"),
                foodGroup.artifactId("fancyfoods.datasource").
                    version("1.0.0"));
        Option[] server = PaxConfigurer.getServerPlatform();
        Option[] options = OptionUtils.combine(fancyFoodsBundles,
                                           server);

        return options;
    }
}
