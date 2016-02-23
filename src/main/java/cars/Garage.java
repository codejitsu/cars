package cars;

import cars.exception.AlreadyInGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents Garage with vehicles.
 */
public class Garage {
    private final static int DEFAULT_PARKING_LEVEL_COUNT = 1;
    private final static int DEFAULT_PARKING_SPACES_PER_LEVEL_COUNT = 100;

    private final int parkingLevelCount;
    private final int parkingSpacesPerLevel;

    private final int maxParkingPlacesCount;

    private Map<VehicleId, VehicleLocation> cars = new HashMap<>();

    private Map<Integer, VehicleId[]> places = new HashMap<>();

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

        for (int i = 1; i <= this.parkingLevelCount; i++) {
            places.put(Integer.valueOf(i), new VehicleId[this.parkingSpacesPerLevel]);
        }
    }

    public boolean contains(final Vehicle vehicle) {
        return cars.containsKey(vehicle.getVehicleId());
    }

    public int getParkingLevelCount() {
        return this.parkingLevelCount;
    }

    public int getParkingSpacesPerLevel() {
        return this.parkingSpacesPerLevel;
    }

    public void tryEnter(final Vehicle vehicle) throws NoFreeParkingLotsException, AlreadyInGarageException {
        if (vehicle.isInGarage(this)) {
            throw new AlreadyInGarageException(this);
        }

        if (this.getNumberOfFreeParkingSlots() == 0) {
            throw new NoFreeParkingLotsException(maxParkingPlacesCount, this);
        }

        if (!this.contains(vehicle)) {
            final VehicleLocation freeLocation = getFirstFreeSpace();
            this.cars.put(vehicle.getVehicleId(), freeLocation);

            final VehicleId[] level = this.places.get(Integer.valueOf(freeLocation.getLevel()));
            level[freeLocation.getSpace()] = vehicle.getVehicleId();
        }
    }

    public void tryExit(final Vehicle vehicle) throws NotInGarageException {
        if (!vehicle.isInGarage(this)) {
           throw new NotInGarageException(this);
        }

        if (this.contains(vehicle)) {
            final VehicleLocation location = this.getLocation(vehicle.getVehicleId());

            this.cars.remove(vehicle.getVehicleId());
            final VehicleId[] level = this.places.get(Integer.valueOf(location.getLevel()));
            level[location.getSpace()] = null;
        }
    }

    private VehicleLocation getFirstFreeSpace() {
        for (int i = 1; i <= this.getParkingLevelCount(); i++) {
            final VehicleId[] level = this.places.get(Integer.valueOf(i));

            for (int j = 0; j < level.length; j++) {
                if (level[j] == null) {
                    return new VehicleLocation(this, i, j);
                }
            }
        }

        return null;
    }

    public int getNumberOfFreeParkingSlots() {
        return this.maxParkingPlacesCount - this.cars.size();
    }

    public VehicleLocation getLocation(final VehicleId vehicleId) {
        return this.cars.get(vehicleId);
    }
}
