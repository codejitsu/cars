package cars;

/**
 * The base class for all garage exceptions.
 */
public abstract class GarageException extends Exception {
    private final Garage garage;

    protected GarageException(final Garage garage) {
        this.garage = garage;
    }

    public Garage getGarage() {
        return garage;
    }
}
