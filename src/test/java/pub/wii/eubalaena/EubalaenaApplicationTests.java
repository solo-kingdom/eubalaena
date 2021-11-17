package pub.wii.eubalaena;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import pub.wii.eubalaena.config.TestConfig;

@EnableAutoConfiguration
@SpringBootTest(classes = TestConfig.class)
class EubalaenaApplicationTests {
    @Test
    void contextLoads() {
    }
}
