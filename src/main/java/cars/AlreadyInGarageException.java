package cars;

public class AlreadyInGarageException extends GarageException {
    protected AlreadyInGarageException(final Garage garage) {
        super(garage);
    }
}
