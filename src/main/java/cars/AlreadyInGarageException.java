package cars;

/**
 * Created by alexsverdelov on 20.02.16.
 */
public class AlreadyInGarageException extends GarageException {
    protected AlreadyInGarageException(final Garage garage) {
        super(garage);
    }
}
