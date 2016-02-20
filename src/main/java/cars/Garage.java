package cars;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents Garage with vehicles.
 */
public class Garage {
    private final static int DEFAULT_PARKING_LEVEL_COUNT = 1;
    private final static int DEFAULT_PARKING_SPACES_PER_LEVEL_COUNT = 100;

    private final int parkingLevelCount;
    private final int parkingSpacesPerLevel;

    private final int maxParkingPlacesCount;

    private Set<VehicleId> cars = new HashSet<>();

    public Garage() {
        this(DEFAULT_PARKING_LEVEL_COUNT, DEFAULT_PARKING_SPACES_PER_LEVEL_COUNT);
    }

    public Garage(final int parkingLevelCount) {
        this(parkingLevelCount, DEFAULT_PARKING_SPACES_PER_LEVEL_COUNT);
    }

    public Garage(final int parkingLevelCount, final int parkingSpacesPerLevel) {
        //TODO add validation
        this.parkingLevelCount = parkingLevelCount;
        this.parkingSpacesPerLevel = parkingSpacesPerLevel;
        this.maxParkingPlacesCount = parkingLevelCount * parkingSpacesPerLevel;
    }

    public boolean contains(final Vehicle vehicle) {
        return cars.contains(vehicle.getVehicleId());
    }

    public int getParkingLevelCount() {
        return this.parkingLevelCount;
    }

    public int getParkingSpacesPerLevel() {
        return this.parkingSpacesPerLevel;
    }

    public void tryEnter(final Vehicle vehicle) throws NoFreeParkingLotsException {
        //TODO validation vehicle.isInGarage
        //TODO add test for available places = av. places - 1

        if (this.getNumberOfFreeParkingSlots() == 0) {
            throw new NoFreeParkingLotsException(maxParkingPlacesCount, this);
        }

        if (!this.contains(vehicle)) {
            this.cars.add(vehicle.getVehicleId());
        }
    }

    public void tryExit(final Vehicle vehicle) {
        //TODO validation vehicle.isInGarage
        //TODO add test for available places = av. places + 1

        if (this.contains(vehicle)) {
            this.cars.remove(vehicle.getVehicleId());
        }
    }

    public int getNumberOfFreeParkingSlots() {
        return this.maxParkingPlacesCount - this.cars.size();
    }
}
