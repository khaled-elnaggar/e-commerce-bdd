package org.example.infrastructure.httpclients.inventory;

import org.example.application.gateways.InventoryGateway;
import org.springframework.stereotype.Component;

@Component
public class InventoryAdapter implements InventoryGateway {
  private final InventoryClient inventoryClient;

  public InventoryAdapter(InventoryClient inventoryClient) {
    this.inventoryClient = inventoryClient;
  }

  @Override
  public ProductInfo getProductDetails(String id) {
    return inventoryClient.getProductDetails(id);
  }
}
