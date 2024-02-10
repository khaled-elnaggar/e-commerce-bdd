package org.example.infrastructure.spring;

import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.application.usecases.CheckoutUseCase;
import org.example.application.usecases.CheckoutUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
  @Bean
  public CheckoutUseCase checkoutUseCase(InventoryGateway inventoryService,
                                         PaymentGateway paymentService,
                                         ReceiptRepository receiptRepository) {
    return new CheckoutUseCaseImpl(inventoryService, paymentService,
            receiptRepository);

  }


}
