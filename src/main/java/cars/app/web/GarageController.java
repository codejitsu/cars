package cars.app.web;

import cars.app.service.GarageRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
