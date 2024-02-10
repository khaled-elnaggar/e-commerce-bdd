package org.example.infrastructure.httpclients.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessfulPaymentDetails {
  String transactionId;
}
