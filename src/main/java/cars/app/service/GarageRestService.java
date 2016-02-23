package cars.app.service;

import cars.*;
import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Garage REST endpoint.
 */
@Component
public class GarageRestService {
    @Autowired
    private GarageService garageService;

    public String getCheckService() {
        return "Garage REST Service OK";
    }

    public VehicleLocation enterVehicle(final String vehicleType, final String vehicleId) throws NoFreeParkingLotsException, AlreadyInGarageException {
        System.out.println("REST: enterVehicle(" + vehicleType + ", " + vehicleId + ")");

        if ("car".equals(vehicleType.toLowerCase())) {
            return this.garageService.enterVehicle(new Car(new VehicleId(vehicleId)));
        } else if ("motorbike".equals(vehicleType.toLowerCase())) {
            return this.garageService.enterVehicle(new Motorbike(new VehicleId(vehicleId)));
        }

        return null;
    }

    public VehicleLocation exitVehicle(final String vehicleType, final String vehicleId) throws NotInGarageException, InvalidGarageException {
        System.out.println("REST: exitVehicle(" + vehicleType + ", " + vehicleId + ")");

        if ("car".equals(vehicleType.toLowerCase())) {
            return this.garageService.exitVehicle(new Car(new VehicleId(vehicleId)));
        } else if ("motorbike".equals(vehicleType.toLowerCase())) {
            return this.garageService.exitVehicle(new Motorbike(new VehicleId(vehicleId)));
        }

        return null;
    }
}
