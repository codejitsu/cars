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

    public void enter(final Garage garage) {
        if (this.isInGarage()) {
            //TODO add exception
        } else {
            garage.trySet(this);
            this.inGarage = true;
        }
    }
}
