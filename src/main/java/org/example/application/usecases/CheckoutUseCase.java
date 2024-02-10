package org.example.application.usecases;

import org.example.presentation.rest.dto.Order;
import org.example.presentation.rest.dto.Receipt;

public interface CheckoutUseCase {
  Receipt checkout(Order order, String authorization);
}
