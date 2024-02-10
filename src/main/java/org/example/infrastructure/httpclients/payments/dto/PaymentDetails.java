package org.example.infrastructure.httpclients.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentDetails {
  String transactionId;
}
