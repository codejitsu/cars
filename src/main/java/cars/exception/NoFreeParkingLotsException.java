package cars.exception;

import cars.Garage;

/**
 * In garage there are no more free slots available.
 */
public class NoFreeParkingLotsException extends GarageException {
    private int maxParkingLotsAvailable;

    public NoFreeParkingLotsException(final int maxParkingLotsAvailable, final Garage garage) {
        super(garage);
        this.maxParkingLotsAvailable = maxParkingLotsAvailable;
    }

    public int getMaxParkingLotsAvailable() {
        return maxParkingLotsAvailable;
    }
}
