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

    public void enter(final Garage garage) throws NoFreeParkingLotsException, AlreadyInGarageException {
        if (this.isInGarage()) {
            throw new AlreadyInGarageException(this.garage);
        } else {
            garage.tryEnter(this);
            this.garage = garage;
        }
    }

    public void exit(final Garage garage) throws NotInGarageException, InvalidGarageException {
        if (!this.isInGarage()) {
            throw new NotInGarageException(garage);
        } else if (!this.garage.equals(garage)) {
            throw new InvalidGarageException(this.garage);
        }

        garage.tryExit(this);
        this.garage = null;
    }

    public Garage getGarage() {
        return this.garage;
    }
}
