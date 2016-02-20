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
    public void testGetGarageDefault() {
        final Vehicle car = new Car();

        Assert.assertNull(car.getGarage());
    }

    @Test
    public void testGetGarageAfterEnter() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(car.isInGarage());

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertEquals(garage, car.getGarage());
    }

    @Test
    public void testVehicleCannotEnterTheSameGarageTwice() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertTrue(garage.contains(car));
        Assert.assertEquals(garage, car.getGarage());

        try {
            car.enter(garage);
        } catch (final AlreadyInGarageException ise) {
            Assert.assertEquals(garage, ise.getGarage());
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail();
        }
    }

    @Test
    public void testVehicleEnterGarage() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(garage.contains(car));
        Assert.assertFalse(car.isInGarage());

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertTrue(garage.contains(car));
    }

    @Test
    public void testVehicleExitGarage() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(garage.contains(car));
        Assert.assertFalse(car.isInGarage());

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertTrue(garage.contains(car));
        Assert.assertEquals(garage, car.getGarage());

        try {
            car.exit(garage);
        } catch (final NotInGarageException e) {
            Assert.fail("Vehicle should be in garage.");
        } catch (final InvalidGarageException e) {
            Assert.fail();
        }

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
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertTrue(garage.contains(car1));

        try {
            car2.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertTrue(garage.contains(car2));

        try {
            car3.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.assertEquals(2, e.getMaxParkingLotsAvailable());
            Assert.assertEquals(garage, e.getGarage());
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
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
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertEquals(19, garage.getNumberOfFreeParkingSlots());

        try {
            car2.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
        }

        Assert.assertEquals(18, garage.getNumberOfFreeParkingSlots());

        try {
            car3.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside garage.");
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

    @Test
    public void testVehicleCannotExitGarageFromOutside() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(car.isInGarage());
        Assert.assertFalse(garage.contains(car));

        try {
            car.exit(garage);
        } catch (final NotInGarageException nig) {
            Assert.assertEquals(garage, nig.getGarage());
        } catch (final InvalidGarageException e) {
            Assert.fail();
        }
    }

    @Test
    public void testVehicleTryEnterCheck() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(car.isInGarage());
        Assert.assertFalse(garage.contains(car));

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots.");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside.");
        }

        try {
            garage.tryEnter(car);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots.");
        } catch (final AlreadyInGarageException e) {
            Assert.assertEquals(garage, e.getGarage());
        }
    }

    @Test
    public void testVehicleTryExitCheck() {
        final Garage garage = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(car.isInGarage());
        Assert.assertFalse(garage.contains(car));

        try {
            garage.tryExit(car);
        } catch (final NotInGarageException nige) {
            Assert.assertEquals(garage, nige.getGarage());
        }
    }

    @Test
    public void testVehicleTryExitOtherGarage() {
        final Garage garage1 = new Garage(10, 100);
        final Garage garage2 = new Garage(10, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(car.isInGarage());
        Assert.assertFalse(garage1.contains(car));
        Assert.assertFalse(garage2.contains(car));

        try {
            car.enter(garage1);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots.");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside.");
        }

        Assert.assertTrue(car.isInGarage());
        Assert.assertEquals(garage1, car.getGarage());
        Assert.assertTrue(garage1.contains(car));

        try {
            car.exit(garage2);
        } catch (final NotInGarageException e) {
            Assert.fail("Vehicle is garage.");
        } catch (final InvalidGarageException iga) {
            Assert.assertEquals(garage1, iga.getGarage());
        }
    }

    @Test
    public void testAfterEnterEveryVehicleHasALocation() {
        final Garage garage = new Garage(1, 100);
        final Vehicle car = new Car();

        Assert.assertFalse(car.isInGarage());
        Assert.assertFalse(garage.contains(car));

        Assert.assertNull(garage.getLocation(car.getVehicleId()));

        try {
            car.enter(garage);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("Garage should have free parking slots.");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Vehicle is outside.");
        }

        final VehicleLocation location = garage.getLocation(car.getVehicleId());

        Assert.assertNotNull(location);
    }
}
