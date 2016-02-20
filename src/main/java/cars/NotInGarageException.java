package cars;

/**
 * A vehicle outside garage tries to exit a garage.
 */
public class NotInGarageException extends GarageException {
    protected NotInGarageException(final Garage garage) {
        super(garage);
    }
}
