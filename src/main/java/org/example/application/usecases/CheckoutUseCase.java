package org.example.application.usecases;

import org.example.presentation.rest.dto.OrderRequest;

public interface CheckoutUseCase {
  void checkout(OrderRequest orderRequest);
}
