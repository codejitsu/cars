package cars;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * All service tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GarageServiceSpringTestConfiguration.class)
public class GarageServiceTest {
    @Autowired
    private GarageServiceImpl garageService;

    @Test
    public void testWiring() {
        Assert.assertNotNull(this.garageService);
    }
}
