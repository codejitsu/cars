package cars.app.service;

import org.springframework.stereotype.Component;

/**
 * Garage REST endpoint.
 */
@Component
public class GarageRestService {
    public String getCheckService() {
        return "Garage REST Service OK";
    }
}
