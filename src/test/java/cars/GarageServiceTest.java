package cars;

import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * All service tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GarageServiceSpringTestConfiguration.class)
public class GarageServiceTest {
    @Autowired
    private GarageServiceImpl garageService;

    @Test
    public void testWiring() {
        Assert.assertNotNull(this.garageService);
        Assert.assertNotNull(this.garageService.getGarage());
    }

    @Test
    public void testConfiguration() {
        Assert.assertEquals(5, this.garageService.getGarage().getParkingLevelCount());
        Assert.assertEquals(50, this.garageService.getGarage().getParkingSpacesPerLevel());
    }

    @Test
    public void testEnterVehicle() {
        final VehicleId id = VehicleId.createNewRandomId();
        VehicleLocation location = null;

        try {
            location = garageService.enterVehicle(new Car(id));
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("There should be free lots.");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Car is outside the garage yet.");
        }

        Assert.assertNotNull(location);
    }

    @Test
    public void testExitVehicle() {
        final VehicleId id = VehicleId.createNewRandomId();
        VehicleLocation location = null;
        VehicleLocation oldLocation = null;
        final Car vehicle = new Car(id);

        try {
            location = garageService.enterVehicle(vehicle);

            oldLocation = garageService.exitVehicle(vehicle);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("There should be free lots.");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Car is outside the garage yet.");
        } catch (final InvalidGarageException e) {
            Assert.fail("The garage is the same");
        } catch (final NotInGarageException e) {
            Assert.fail("The car should be in the garage.");
        }

        Assert.assertNotNull(location);
        Assert.assertNotNull(oldLocation);
        Assert.assertEquals(location, oldLocation);
    }

    @Test
    public void testGetLocationNoVehicle() {
        final VehicleId id = VehicleId.createNewRandomId();

        Assert.assertNull(garageService.getLocation(id));
    }

    @Test
    public void testGetLocation() {
        final VehicleId id = VehicleId.createNewRandomId();
        VehicleLocation location1 = null;
        VehicleLocation location2 = null;
        final Car vehicle = new Car(id);

        try {
            location1 = garageService.enterVehicle(vehicle);
            location2 = garageService.getLocation(id);
        } catch (final NoFreeParkingLotsException e) {
            Assert.fail("There should be free lots.");
        } catch (final AlreadyInGarageException e) {
            Assert.fail("Car is outside the garage yet.");
        }

        Assert.assertNotNull(location1);
        Assert.assertNotNull(location2);
        Assert.assertEquals(location1, location2);
    }
}
