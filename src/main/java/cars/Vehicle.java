package cars;

/*
 *  Base class for all vehicle types.
 */
public abstract class Vehicle {
    /*
     * Unchangable vehicle id.
     */
    private final VehicleId vehicleId = VehicleId.createNewId();

    private boolean inGarage = false;

    public VehicleId getVehicleId() {
        return this.vehicleId;
    }

    public boolean isInGarage() {
        return inGarage;
    }

    public void setInGarage(final boolean inGarage) {
        this.inGarage = inGarage;
    }
}
