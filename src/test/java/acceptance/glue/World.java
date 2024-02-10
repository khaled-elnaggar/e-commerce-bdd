package acceptance.glue;

import io.cucumber.spring.ScenarioScope;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class World {
  public static InventoryGateway inventoryGateway = Mockito.mock(InventoryGateway.class);
  public static PaymentGateway paymentGateway = Mockito.mock(PaymentGateway.class);
  public static ReceiptRepository receiptRepository = Mockito.mock(ReceiptRepository.class);

  @Bean
  InventoryGateway getInventoryGateway(){
    return inventoryGateway;
  }

  @Bean
  PaymentGateway getPaymentGateway(){
    return paymentGateway;
  }

  @Bean
  ReceiptRepository getReceiptRepository(){
    return receiptRepository;
  }
}
