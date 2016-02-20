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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleLocation location = (VehicleLocation) o;

        if (level != location.level) return false;
        if (space != location.space) return false;
        return garage != null ? garage.equals(location.garage) : location.garage == null;

    }

    @Override
    public int hashCode() {
        int result = garage != null ? garage.hashCode() : 0;
        result = 31 * result + level;
        result = 31 * result + space;
        return result;
    }
}
