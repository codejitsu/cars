package cars;

/*
 *  Car class.
 */
public class Car extends Vehicle {
    public Car() {
        super(VehicleType.CAR);
    }

    public Car(final VehicleId vehicleId) {
        super(VehicleType.CAR, vehicleId);
    }
}
