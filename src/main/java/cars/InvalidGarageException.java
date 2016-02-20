package cars;

/**
 * Vehicle tries to exit a different garage.
 */
public class InvalidGarageException extends GarageException {
    protected InvalidGarageException(final Garage garage) {
        super(garage);
    }
}
