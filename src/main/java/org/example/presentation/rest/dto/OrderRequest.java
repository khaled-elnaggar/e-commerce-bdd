package org.example.presentation.rest.dto;

import lombok.Data;

@Data
public class OrderRequest {
  private String orderId;
  private Cart cart;

  //TODO split both fields in Successful order subclass
  private String transactionId;
  private int totalPaid;

  public OrderRequest(String orderId) {
    this.orderId = orderId;
  }
}
