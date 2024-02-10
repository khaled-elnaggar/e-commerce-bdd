package acceptance;

import fitnesseMain.Arguments;
import fitnesseMain.FitNesseMain;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


@CucumberContextConfiguration
@ComponentScan
@EnableAutoConfiguration
@SpringBootTest(classes = ContextConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringFitnesseTest {

  @EventListener(ApplicationReadyEvent.class)
  public void fitNesseMain() throws Exception {
    FitNesseMain fitNesseMain = new FitNesseMain();
    Arguments fitnesseArguments = new Arguments("-p", "8005", "-d", ".");
    fitNesseMain.launchFitNesse(fitnesseArguments);
    System.out.println("starting");
  }

  @Test
  void setup() {
    System.out.println("hehe");
  }
}

@Configuration
@ComponentScan(basePackages = "org.example") // Adjust the package name
class ContextConfig {
  // Your Spring configuration
  // @Bean(initMethod = "start", destroyMethod = "stop")
}
