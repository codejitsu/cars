package cars;

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
}
