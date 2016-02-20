package cars;

/*
 *  Base class for all vehicle types.
 */
public abstract class Vehicle {
    /*
     * Unchangable vehicle id.
     */
    private final VehicleId vehicleId;

    public Vehicle() {
        this.vehicleId = new VehicleId();
    }

    public VehicleId getVehicleId() {
        return this.vehicleId;
    }
}
