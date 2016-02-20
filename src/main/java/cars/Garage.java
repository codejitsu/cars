package cars;

/**
 * Represents Garage with vehicles.
 */
public class Garage {
    private int parkingLevelCount;

    public Garage(final int parkingLevelCount) {
        //TODO add validation
        this.parkingLevelCount = parkingLevelCount;
    }

    public Garage() {
        this.parkingLevelCount = 1;
    }

    public boolean contains(final Vehicle vehicle) {
        return false;
    }

    public int getParkingLevelCount() {
        return this.parkingLevelCount;
    }
}
