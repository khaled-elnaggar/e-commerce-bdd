package org.example.application.usecases;

import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;

public interface CheckoutUseCase {
  Receipt checkout(OrderRequest orderRequest);
}
