package org.example.infrastructure.spring;

import org.example.application.gateways.AuthGateway;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.NotificationsGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.application.usecases.CheckoutUseCaseImpl;
import org.example.application.usecases.CheckoutUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
  @Bean
  public CheckoutUseCase checkoutUseCase(AuthGateway authService, InventoryGateway inventoryService,
                                         PaymentGateway paymentService, NotificationsGateway notificationsService,
                                         ReceiptRepository receiptRepository) {
    return new CheckoutUseCaseImpl(authService, inventoryService, paymentService,
            notificationsService, receiptRepository);

  }


}
