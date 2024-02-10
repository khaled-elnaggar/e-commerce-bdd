package org.example.presentation.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;

@Data
@AllArgsConstructor
public class Order {
  private final PaymentDetails paymentDetails;
  private final int paidAmount;
}
