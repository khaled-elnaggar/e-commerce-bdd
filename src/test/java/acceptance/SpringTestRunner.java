package acceptance;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;

@CucumberContextConfiguration
@SpringBootTest(classes = {ContextConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringTestRunner {
}

@Configuration
@ComponentScan(basePackages = {"org.example", "acceptance"}) // Adjust the package name
class ContextConfig {
  @Bean
  @Primary
//  @Scope("cucumber-glue")
  InventoryGateway getInventoryGateway() {
    return Mockito.mock(InventoryGateway.class);
  }

  @Bean
  @Primary
//  @Scope("cucumber-glue")
  PaymentGateway getPaymentGateway() {
    return Mockito.mock(PaymentGateway.class);
  }

  @Bean
  @Primary
//  @Scope("cucumber-glue")
  ReceiptRepository getReceiptRepository() {
    return Mockito.mock(ReceiptRepository.class);
  }

}