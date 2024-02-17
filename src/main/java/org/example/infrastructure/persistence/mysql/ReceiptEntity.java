package org.example.infrastructure.persistence.mysql;

import lombok.Data;
import org.example.presentation.rest.dto.Order;

import javax.persistence.*;

@Entity
@Data
@Table(name = "receipt")
public class ReceiptEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String transactionId;
  private int paidAmount;

  public ReceiptEntity(Order order) {
    this.transactionId = order.getPaymentDetails().getTransactionId();
    this.paidAmount = order.getPaidAmount();
  }

}
