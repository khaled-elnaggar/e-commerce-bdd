package org.example.presentation.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestedProduct {
  private String id;
  private int quantity;
}
