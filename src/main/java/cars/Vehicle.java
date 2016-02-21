package cars;

import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;

/*
 *  Base class for all vehicle types.
 */
public abstract class Vehicle {
    public enum VehicleType {
        CAR,
        MOTORBIKE
    }

    /*
     * Unchangable vehicle id.
     */
    private final VehicleId vehicleId;

    private VehicleType type;

    private Garage garage;

    protected Vehicle(final VehicleType type) {
        this(type, VehicleId.createNewRandomId());
    }

    public Vehicle(final VehicleType vehicleType, final VehicleId vehicleId) {
        this.type = vehicleType;
        this.vehicleId = vehicleId;
    }

    public VehicleType getType() {
        return type;
    }

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
