package org.example.presentation.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Receipt {
  private String transactionId;
  private int paidAmount;
}
