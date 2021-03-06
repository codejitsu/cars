package cars.app.web;

import cars.SimpleLocation;
import cars.VehicleLocation;
import cars.app.service.GarageRestService;
import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * Garage app controller.
 */
@Controller
public class GarageController {
    @Autowired
    private GarageRestService restService;

    @RequestMapping("/")
    @ResponseBody
    public String checkService() {
        return this.restService.getCheckService();
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/{vehicleType}/{vehicleId}/enter"})
    @ResponseBody
    public ResponseEntity<SimpleLocation> enterVehicle(@PathVariable String vehicleType,
                                                       @PathVariable String vehicleId) throws NoFreeParkingLotsException,
            AlreadyInGarageException {
        return new ResponseEntity<>(this.restService.enterVehicle(vehicleType, vehicleId).getSimpleLocation(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/{vehicleType}/{vehicleId}/exit"})
    @ResponseBody
    public ResponseEntity<SimpleLocation> exitVehicle(@PathVariable String vehicleType,
                                                      @PathVariable String vehicleId) throws NotInGarageException, InvalidGarageException {
        return new ResponseEntity<>(this.restService.exitVehicle(vehicleType, vehicleId).getSimpleLocation(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{vehicleType}/{vehicleId}/location"})
    @ResponseBody
    public ResponseEntity<SimpleLocation> getVehicleLocaton(@PathVariable String vehicleType,
                                                            @PathVariable String vehicleId) {
        final Optional<VehicleLocation> location = this.restService.getLocation(vehicleType, vehicleId);

        if (location.isPresent()) {
            return new ResponseEntity<>(location.get().getSimpleLocation(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((SimpleLocation) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/free"})
    @ResponseBody
    public ResponseEntity<Integer> freeSlots() {
        return new ResponseEntity<>(this.restService.getFreeSlots(), HttpStatus.OK);
    }
}
