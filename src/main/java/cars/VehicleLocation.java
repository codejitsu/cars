package cars;

/**
 * Vehicle location inside a garage.
 */
public class VehicleLocation {
    private final Garage garage;
    private final int level;
    private final int space;

    public VehicleLocation(Garage garage, int level, int space) {
        this.garage = garage;
        this.level = level;
        this.space = space;
    }

    public Garage getGarage() {
        return garage;
    }

    public int getLevel() {
        return level;
    }

    public int getSpace() {
        return space;
    }
}
