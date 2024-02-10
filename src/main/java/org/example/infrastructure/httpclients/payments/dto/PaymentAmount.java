package org.example.infrastructure.httpclients.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentAmount {
  private int amount;
}
