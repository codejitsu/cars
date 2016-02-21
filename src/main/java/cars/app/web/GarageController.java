package cars.app.web;

import cars.VehicleLocation;
import cars.app.service.GarageRestService;
import cars.exception.AlreadyInGarageException;
import cars.exception.NoFreeParkingLotsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public VehicleLocation enterVehicle(@PathVariable String vehicleType,
                                        @PathVariable String vehicleId) throws NoFreeParkingLotsException,
            AlreadyInGarageException {
        return this.restService.enterVehicle(vehicleType, vehicleId);
    }
}
