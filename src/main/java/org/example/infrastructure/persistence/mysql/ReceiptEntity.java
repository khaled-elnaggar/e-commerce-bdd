package org.example.infrastructure.persistence.mysql;

import lombok.Data;
import org.example.presentation.rest.dto.Receipt;
import org.example.presentation.rest.dto.SuccessfulOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "receipt")
public class ReceiptEntity {
  @Id
  private String id;
  private String transactionId;
  private int paidAmount;

  public ReceiptEntity(SuccessfulOrder successfulOrder) {
    this.id = successfulOrder.getOrderId();
    this.transactionId = successfulOrder.getPaymentDetails().getTransactionId();
    this.paidAmount = successfulOrder.getPaidAmount();
  }

  public Receipt toUseCaseModel() {
    return Receipt.builder()
            .id(this.id)
            .transactionId(this.transactionId)
            .paidAmount(this.paidAmount)
            .build();
  }

}
