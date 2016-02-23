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
        assertThat(entity.getBody().getSpace()).isNotNegative();
    }

    @Test
    public void testExitVehicle() throws Exception {
        final RestTemplate restTemplateEnter = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityEnter = restTemplateEnter.exchange(
                "http://localhost:" + this.port + "/car/123456/enter", HttpMethod.POST, null,
                SimpleLocation.class);

        assertThat(entityEnter.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entityEnter.getBody()).isNotNull();
        assertThat(entityEnter.getBody().getLevel()).isEqualTo(1);
        assertThat(entityEnter.getBody().getSpace()).isNotNegative();

        final RestTemplate restTemplateExit = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityExit = restTemplateExit.exchange(
                "http://localhost:" + this.port + "/car/123456/exit", HttpMethod.POST, null,
                SimpleLocation.class);

        assertThat(entityExit.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entityExit.getBody()).isNotNull();
        assertThat(entityExit.getBody().getLevel()).isEqualTo(1);
        assertThat(entityExit.getBody().getSpace()).isNotNegative();
    }

    @Test
    public void testGetLocation() throws Exception {
        final RestTemplate restTemplateEnter = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityEnter = restTemplateEnter.exchange(
                "http://localhost:" + this.port + "/car/1234567/enter", HttpMethod.POST, null,
                SimpleLocation.class);

        assertThat(entityEnter.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entityEnter.getBody()).isNotNull();
        assertThat(entityEnter.getBody().getLevel()).isEqualTo(1);
        assertThat(entityEnter.getBody().getSpace()).isNotNegative();

        final RestTemplate restTemplateGetLocation = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityLocation = restTemplateGetLocation.exchange(
                "http://localhost:" + this.port + "/car/1234567/location", HttpMethod.GET, null,
                SimpleLocation.class);

        assertThat(entityLocation.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entityLocation.getBody()).isNotNull();
        assertThat(entityLocation.getBody().getLevel()).isEqualTo(1);
        assertThat(entityLocation.getBody().getSpace()).isNotNegative();

        assertThat(entityEnter.getBody()).isEqualTo(entityLocation.getBody());
    }

    @Test
    public void testGetLocation404() throws Exception {
        final RestTemplate restTemplateGetLocation = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityLocation = restTemplateGetLocation.exchange(
                "http://localhost:" + this.port + "/car/1/location", HttpMethod.GET, null,
                SimpleLocation.class);

        assertThat(entityLocation.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void test401InvalidVehicleType() throws Exception {
        final RestTemplate restTemplateEnter = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityEnter = restTemplateEnter.exchange(
                "http://localhost:" + this.port + "/test/1234567/enter", HttpMethod.POST, null,
                SimpleLocation.class);

        assertThat(entityEnter.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void test401InvalidVehicleNumber() throws Exception {
        final RestTemplate restTemplateEnter = new TestRestTemplate();
        final ResponseEntity<SimpleLocation> entityEnter = restTemplateEnter.exchange(
                "http://localhost:" + this.port + "/motorbike/test/enter", HttpMethod.POST, null,
                SimpleLocation.class);

        assertThat(entityEnter.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
