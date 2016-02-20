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

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertTrue(garage.contains(car));
    }

    @Test
    public void testVehicleExitGarage() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(garage.contains(car));

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertTrue(garage.contains(car));

        car.exit(garage);

        Assert.assertFalse(garage.contains(car));
    }

    @Test
    public void testGarageShouldCheckAvailablePlaces() {
        final Garage garage = new Garage(1, 2);
        final Vehicle car1 = new Car();
        final Vehicle car2 = new Car();
        final Vehicle car3 = new Car();

        try {
            car1.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertTrue(garage.contains(car1));

        try {
            car2.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertTrue(garage.contains(car2));

        try {
            car3.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.assertEquals(2, e.getMaxParkingLotsAvailable());
            Assert.assertEquals(garage, e.getGarage());
        }

        Assert.assertTrue(garage.contains(car1));
        Assert.assertTrue(garage.contains(car2));
        Assert.assertFalse(garage.contains(car3));
    }

    @Test
    public void testNumberOfFreeParkingSlotsIsQueryable() {
        final Garage garage = new Garage(1, 20);
        final Vehicle car1 = new Car();
        final Vehicle car2 = new Car();
        final Vehicle car3 = new Car();

        try {
            car1.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertEquals(19, garage.getNumberOfFreeParkingSlots());

        try {
            car2.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertEquals(18, garage.getNumberOfFreeParkingSlots());

        try {
            car3.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        }

        Assert.assertEquals(17, garage.getNumberOfFreeParkingSlots());
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
