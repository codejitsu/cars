package cars.exception;

import cars.Garage;

public class AlreadyInGarageException extends GarageException {
    public AlreadyInGarageException(final Garage garage) {
        super(garage);
    }
}
