package org.example.presentation.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.infrastructure.httpclients.payments.dto.SuccessfulPaymentDetails;

@Data
@AllArgsConstructor
public class SuccessfulOrder {
  private final String orderId;
  private final SuccessfulPaymentDetails paymentDetails;
  private final int paidAmount;
}
