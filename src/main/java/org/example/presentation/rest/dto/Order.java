package org.example.presentation.rest.dto;

import lombok.Data;

@Data
public class Order {
  private String orderId;
  private Cart cart;
  //TODO split both fields in Successful order subclass
  private String transactionId;
  private double totalPaid;

  public Order(String orderId) {
    this.orderId = orderId;
  }
}
