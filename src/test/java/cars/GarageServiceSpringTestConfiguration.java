package cars;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Garage service spring cofiguration.
 */
@Configuration
@PropertySource("classpath:garage.properties")
public class GarageServiceSpringTestConfiguration {
    @Value("${garage.levels}")
    private int levels;

    @Value("${garage.places.level}")
    private int placesPerLevel;

    @Bean
    public GarageService garageService() {
        return new GarageServiceImpl();
    }

    @Bean
    public Garage garage() {
        return new Garage(levels, placesPerLevel);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
