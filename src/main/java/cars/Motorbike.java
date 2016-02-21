package cars;

/**
 * Motorbike class.
 */
public class Motorbike extends Vehicle {
    public Motorbike() {
        super(VehicleType.MOTORBIKE);
    }

    public Motorbike(final VehicleId vehicleId) {
        super(VehicleType.MOTORBIKE, vehicleId);
    }
}
