package org.example.application.gateways;

import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;

public interface PaymentGateway {
  PaymentDetails makePayment(String authorization, PaymentAmount order);
}
