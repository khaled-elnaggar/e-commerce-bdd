package org.example.presentation.rest.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Receipt {
  private String id;
  private double totalPaid;
}
