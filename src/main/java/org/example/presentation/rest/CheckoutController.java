package org.example.presentation.rest;

import org.example.application.usecases.CheckoutUseCase;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {
  @Autowired
  private CheckoutUseCase checkoutUseCase;

  @PostMapping("/checkout")
  public Receipt checkout(@RequestBody OrderRequest orderRequest) {
    return checkoutUseCase.checkout(orderRequest);
  }
}
