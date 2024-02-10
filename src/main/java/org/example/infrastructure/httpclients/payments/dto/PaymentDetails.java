package org.example.infrastructure.httpclients.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class PaymentDetails {
  String transactionId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaymentDetails that = (PaymentDetails) o;
    return Objects.equals(transactionId, that.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId);
  }
}
