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

    public boolean isInGarage(final Garage garage) {
        return garage.contains(this);
    }

    public void enter(final Garage garage) throws NoFreeParkingLotsException, AlreadyInGarageException {
        if (this.isInGarage(garage)) {
            throw new AlreadyInGarageException(garage);
        } else {
            garage.tryEnter(this);
        }
    }

    public void exit(final Garage garage) throws NotInGarageException, InvalidGarageException {
        if (!this.isInGarage(garage)) {
            throw new NotInGarageException(garage);
        }

        garage.tryExit(this);
    }
}
