package cars;

import cars.app.GarageApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * REST tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GarageApplication.class)
@WebIntegrationTest(randomPort = true)
public class GarageRestServiceTest {
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testCheckService() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate()
                .getForEntity("http://localhost:" + this.port, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Garage REST Service OK");
    }

    @Test
    public void testEnterVehicle() throws Exception {
        final RestTemplate restTemplate = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entity = restTemplate.exchange(
                "http://localhost:" + this.port + "/car/12345/enter", HttpMethod.POST, null,
                SimpleLocation.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getBody()).isNotNull();
        assertThat(entity.getBody().getLevel()).isEqualTo(1);
        assertThat(entity.getBody().getSpace()).isEqualTo(0);
    }
}
