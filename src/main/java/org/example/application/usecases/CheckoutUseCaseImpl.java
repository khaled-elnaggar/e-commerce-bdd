package org.example.application.usecases;

import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.common.ServiceException;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;
import org.example.presentation.rest.dto.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class CheckoutUseCaseImpl implements CheckoutUseCase {
  public CheckoutUseCaseImpl(){}

  @Override
  public void checkout(OrderRequest orderRequest) {

  }
}
