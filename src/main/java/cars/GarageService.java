package cars;

import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;

/**
 * Garage service interface.
 */
public interface GarageService {
    Garage getGarage();

    VehicleLocation enterVehicle(Vehicle vehicle) throws NoFreeParkingLotsException, AlreadyInGarageException;

    VehicleLocation exitVehicle(Vehicle car) throws NotInGarageException, InvalidGarageException;

    VehicleLocation getLocation(VehicleId vehicleId);
}
