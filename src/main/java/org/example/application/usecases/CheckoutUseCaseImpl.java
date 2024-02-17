package org.example.application.usecases;

import org.example.application.gateways.InventoryGateway;
import org.example.presentation.rest.dto.*;

public class CheckoutUseCaseImpl implements CheckoutUseCase {
  private final InventoryGateway inventoryGateway;
  public CheckoutUseCaseImpl(InventoryGateway inventoryGateway) {
    this.inventoryGateway = inventoryGateway;
  }

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    return null;
  }
}
