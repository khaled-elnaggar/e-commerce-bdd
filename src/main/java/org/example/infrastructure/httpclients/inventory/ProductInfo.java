package org.example.infrastructure.httpclients.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductInfo {
  private String id;
  private int inStock;
  private int price;
}
