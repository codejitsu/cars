package cars.exception;

import cars.Garage;

/**
 * A vehicle outside garage tries to exit a garage.
 */
public class NotInGarageException extends GarageException {
    public NotInGarageException(final Garage garage) {
        super(garage);
    }
}
