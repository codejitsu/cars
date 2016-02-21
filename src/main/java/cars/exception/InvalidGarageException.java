package cars.exception;

import cars.Garage;

/**
 * Vehicle tries to exit a different garage.
 */
public class InvalidGarageException extends GarageException {
    public InvalidGarageException(final Garage garage) {
        super(garage);
    }
}
