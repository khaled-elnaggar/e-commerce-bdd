package org.example.application.gateways;

import org.example.infrastructure.httpclients.inventory.ProductInfo;

public interface InventoryGateway {
  ProductInfo getProductDetails(String id);
}
