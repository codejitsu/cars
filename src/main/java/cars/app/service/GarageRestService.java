package cars.app.service;

import cars.*;
import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

        this.validateInput(vehicleType, vehicleId);

        if ("car".equals(vehicleType.toLowerCase())) {
            return this.garageService.enterVehicle(new Car(new VehicleId(vehicleId)));
        } else {
            return this.garageService.enterVehicle(new Motorbike(new VehicleId(vehicleId)));
        }
    }

    public VehicleLocation exitVehicle(final String vehicleType, final String vehicleId) throws NotInGarageException, InvalidGarageException {
        System.out.println("REST: exitVehicle(" + vehicleType + ", " + vehicleId + ")");

        this.validateInput(vehicleType, vehicleId);

        if ("car".equals(vehicleType.toLowerCase())) {
            return this.garageService.exitVehicle(new Car(new VehicleId(vehicleId)));
        } else {
            return this.garageService.exitVehicle(new Motorbike(new VehicleId(vehicleId)));
        }
    }

    public Optional<VehicleLocation> getLocation(final String vehicleType, final String vehicleId) {
        System.out.println("REST: getLocation(" + vehicleType + ", " + vehicleId + ")");

        this.validateInput(vehicleType, vehicleId);

        return Optional.ofNullable(this.garageService.getLocation(new VehicleId(vehicleId)));
    }

    private void validateInput(final String vehicleType, final String vehicleId) {
        if (! ("car".equals(vehicleType.toLowerCase()) || "motorbike".equals(vehicleType.toLowerCase()))) {
            throw new IllegalArgumentException("vehicleType");
        }

        try {
            final int id = Integer.parseInt(vehicleId);

            if (id < 0) {
                throw new IllegalArgumentException("vehicleId");
            }
        } catch (final NumberFormatException nfe) {
            throw new IllegalArgumentException("vehicleId");
        }
    }
}
