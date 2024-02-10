package org.example.infrastructure.httpclients.payments;

import org.example.application.gateways.PaymentGateway;
import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;
import org.springframework.stereotype.Component;

@Component
public class PaymentsAdapter implements PaymentGateway {
  private final PaymentsClient paymentsClient;

  public PaymentsAdapter(PaymentsClient paymentsClient) {
    this.paymentsClient = paymentsClient;
  }

  @Override
  public PaymentDetails makePayment(String authorization, PaymentAmount amount) {
    return paymentsClient.makePayment(authorization, amount);
  }
}
