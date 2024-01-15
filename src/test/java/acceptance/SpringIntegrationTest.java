package acceptance;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@CucumberContextConfiguration
@SpringBootTest(classes = ContextConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {
  // executeGet implementation

}

@Configuration
@ComponentScan(basePackages = "org.example") // Adjust the package name
class ContextConfig {
  // Your Spring configuration
}

