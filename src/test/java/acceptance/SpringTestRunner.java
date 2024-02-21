package acceptance;

import acceptance.helper.RestTestHelper;
import acceptance.helper.TestHelper;
import acceptance.helper.UseCaseTestHelper;
import io.cucumber.spring.CucumberContextConfiguration;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@CucumberContextConfiguration
@SpringBootTest(classes = {ContextConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringTestRunner {
}

@Configuration
@ComponentScan(basePackages = {"org.example", "acceptance"}) // Adjust the package name
class ContextConfig {

  @Bean
  @Primary
  TestHelper getTestHelper(RestTestHelper restTestHelper, UseCaseTestHelper useCaseTestHelper) {
    return useCaseTestHelper;
  }

  @Bean
  @Primary
  InventoryGateway getInventoryGateway() {
    return Mockito.mock(InventoryGateway.class);
  }

  @Bean
  @Primary
  PaymentGateway getPaymentGateway() {
    return Mockito.mock(PaymentGateway.class);
  }

  @Bean
  @Primary
  ReceiptRepository getReceiptRepository() {
    return Mockito.mock(ReceiptRepository.class);
  }

}