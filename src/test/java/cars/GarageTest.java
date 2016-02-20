package cars;

import junit.framework.Assert;
import org.junit.Test;

/**
 * All garage tests are placed here.
 */
public class GarageTest {
    @Test
    public void testIsVehicleInGarage() {
        final Garage garage = new Garage();
        final Vehicle car = new Car();

        Assert.assertFalse(garage.contains(car));
    }
}
