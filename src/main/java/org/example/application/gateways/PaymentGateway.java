package org.example.application.gateways;

import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.SuccessfulPaymentDetails;

public interface PaymentGateway {
  SuccessfulPaymentDetails makePayment(String authorization, PaymentAmount order);
}
