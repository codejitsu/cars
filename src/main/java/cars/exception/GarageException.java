package cars.exception;

import cars.Garage;

/**
 * The base class for all garage exceptions.
 */
public abstract class GarageException extends Exception {
    private final Garage garage;

    public GarageException(final Garage garage) {
        this.garage = garage;
    }

    public Garage getGarage() {
        return garage;
    }
}
