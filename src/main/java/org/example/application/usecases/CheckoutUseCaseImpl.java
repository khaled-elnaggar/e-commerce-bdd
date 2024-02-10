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
  private final InventoryGateway inventoryGateway;
  private final PaymentGateway paymentGateway;
  private final ReceiptRepository receiptRepository;

  public CheckoutUseCaseImpl(InventoryGateway inventoryGateway, PaymentGateway paymentGateway, ReceiptRepository receiptRepository) {
    this.inventoryGateway = inventoryGateway;
    this.paymentGateway = paymentGateway;
    this.receiptRepository = receiptRepository;
  }

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    int amountToBePaid = calculateTotalCost(orderRequest);

    PaymentDetails paymentDetails = paymentGateway.makePayment(orderRequest.getUserAuthorizationToken(), new PaymentAmount(amountToBePaid));
    Order order = new Order(paymentDetails, amountToBePaid);
    receiptRepository.saveReceipt(order);
    return new Receipt(paymentDetails.getTransactionId(), amountToBePaid);
  }

  private int calculateTotalCost(OrderRequest orderRequest) {
    List<String> outOfStock = new ArrayList<>();
    int cost = getTotalCost(orderRequest, outOfStock);
    validateNoItemsOutOfStock(outOfStock);
    return cost;
  }

  private static void validateNoItemsOutOfStock(List<String> outOfStock) {
    if (!outOfStock.isEmpty()) {
      ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, 99, "Out of stock products " + String.join(", ", outOfStock));
      throw new ServiceException(apiError);
    }
  }

  private int getTotalCost(OrderRequest orderRequest, List<String> outOfStock) {
    int cost = 0;
    for (RequestedProduct requestedProduct : orderRequest.getCart().getProducts()) {
      ProductInfo availableProduct = inventoryGateway.getProductDetails(requestedProduct.getId());
      if (availableProduct.getInStock() < requestedProduct.getQuantity()) {
        outOfStock.add(requestedProduct.getId());
      } else {
        cost += availableProduct.getPrice() * requestedProduct.getQuantity();
      }
    }
    return cost;
  }

}

