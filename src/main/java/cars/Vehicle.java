package cars;

/*
 *  Base class for all vehicle types.
 */
public abstract class Vehicle {
    /*
     * Unchangable vehicle id.
     */
    private final VehicleId vehicleId = VehicleId.createNewId();

    private Garage garage;

    public VehicleId getVehicleId() {
        return this.vehicleId;
    }

    public boolean isInGarage() {
        return this.garage != null;
    }

    public void enter(final Garage garage) throws NoFreeParkingLotsException {
        if (this.isInGarage()) {
            //TODO add exception
        } else {
            garage.tryEnter(this);
            this.garage = garage;
        }
    }

    public void exit(Garage garage) {
        if (!this.isInGarage()) {
            //TODO add exception
            //TODO add reference to garage
        } else {
            garage.tryExit(this);
            this.garage = null;
        }
    }

    public Garage getGarage() {
        return this.garage;
    }
}
