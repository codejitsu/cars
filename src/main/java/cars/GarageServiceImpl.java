package cars;

import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Main app service.
 */
public class GarageServiceImpl implements GarageService {
    @Autowired
    private Garage garage;

    @Override
    public Garage getGarage() {
        return this.garage;
    }

    @Override
    public VehicleLocation enterVehicle(final Vehicle vehicle) throws NoFreeParkingLotsException, AlreadyInGarageException {
        vehicle.enter(garage);
        return garage.getLocation(vehicle.getVehicleId());
    }

    @Override
    public VehicleLocation exitVehicle(final Vehicle vehicle) throws NotInGarageException, InvalidGarageException {
        final VehicleLocation location = this.garage.getLocation(vehicle.getVehicleId());

        vehicle.exit(garage);

        return location;
    }

    @Override
    public VehicleLocation getLocation(final VehicleId vehicleId) {
        return this.garage.getLocation(vehicleId);
    }
}
