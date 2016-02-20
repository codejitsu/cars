package cars;

import junit.framework.Assert;
import org.junit.Test;

/**
 * All garage tests are placed here.
 */
public class GarageTest {
    @Test
    public void testIsVehicleInGarage() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(garage.contains(car));
    }

    @Test
    public void testVehicleEnterGarage() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(garage.contains(car));

        car.enter(garage);

        Assert.assertTrue(garage.contains(car));
    }

    @Test
    public void testByDefaultGarageHasOneParkingLevel() {
        final Garage garage = new Garage();

        Assert.assertEquals(1, garage.getParkingLevelCount());
    }

    @Test
    public void testParkingLevelsAreConfigurable() {
        final Garage garage = new Garage(10);

        Assert.assertEquals(10, garage.getParkingLevelCount());
    }

    @Test
    public void testDefaultGarageParkingSpacesCount() {
        final Garage garage = new Garage(10, 100);

        Assert.assertEquals(100, garage.getParkingSpacesPerLevel());
    }

    @Test
    public void testParkingSpacesPerLevelAreConfigurable() {
        final Garage garage = new Garage(10, 100);

        Assert.assertEquals(100, garage.getParkingSpacesPerLevel());
    }
}
