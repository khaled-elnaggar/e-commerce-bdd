package org.example.presentation.rest.dto;

import lombok.Data;

@Data
public class OrderRequest {
  private Cart cart;
  private String userAuthorizationToken;
}
