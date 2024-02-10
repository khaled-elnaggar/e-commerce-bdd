package org.example.infrastructure.persistence.mysql;

import lombok.Data;
import org.example.presentation.rest.dto.Order;
import org.example.presentation.rest.dto.Receipt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "receipt")
public class ReceiptEntity {
  @Id
  private String id;
  private double totalPaid;

  public ReceiptEntity(Order order) {
    this.id = order.getOrderId();
    this.totalPaid = order.getTotalPaid();
  }

  public Receipt toUseCaseModel() {
    return Receipt.builder()
            .id(this.id)
            .totalPaid(this.totalPaid)
            .build();
  }

}
