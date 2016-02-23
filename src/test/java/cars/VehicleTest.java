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
    public void testTypeCar() {
        final Vehicle car = new Car();
        Assert.assertEquals(Vehicle.VehicleType.CAR, car.getType());
    }

    @Test
    public void testVehicleTypeMotorbike() {
        final Vehicle bike = new Motorbike();
        Assert.assertNotNull(bike.getVehicleId());
    }

    @Test
    public void testTypeMotorbike() {
        final Vehicle car = new Motorbike();
        Assert.assertEquals(Vehicle.VehicleType.MOTORBIKE, car.getType());
    }


    @Test
    public void testCarDefaultOutsideGarage() {
        final Vehicle car = new Car();
        final Garage garage = new Garage();

        Assert.assertFalse(car.isInGarage(garage));
    }

    @Test
    public void testMotorbikeDefaultOutsideGarage() {
        final Vehicle bike = new Motorbike();
        final Garage garage = new Garage();

        Assert.assertFalse(bike.isInGarage(garage));
    }
}
