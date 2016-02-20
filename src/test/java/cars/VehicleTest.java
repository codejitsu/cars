package cars;

import org.junit.Assert;
import org.junit.Test;

/*
  All Vehicle specification tests.
 */
public class VehicleTest {
    @Test
    public void testVehicleTypeCar() {
        final Vehicle car = new Car();
        Assert.assertNotNull(car.getVehicleId());
    }

    @Test
    public void testVehicleTypeMotorbike() {
        final Vehicle car = new Motorbike();
        Assert.assertNotNull(car.getVehicleId());
    }
}
