package cars;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Garage service spring cofiguration.
 */
@Configuration
public class GarageServiceSpringTestConfiguration {
    @Bean
    public GarageService garageService() {
        return new GarageServiceImpl();
    }
}
