package org.example.application.usecases;

import org.example.application.gateways.AuthGateway;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.NotificationsGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.common.ServiceException;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;
import org.example.presentation.rest.dto.ApiError;
import org.example.presentation.rest.dto.Order;
import org.example.presentation.rest.dto.RequestedProduct;
import org.example.presentation.rest.dto.Receipt;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class CheckoutUseCaseImpl implements CheckoutUseCase {
  private final AuthGateway authGateway;
  private final InventoryGateway inventoryGateway;
  private final PaymentGateway paymentGateway;
  private final NotificationsGateway notificationsGateway;
  private final ReceiptRepository receiptRepository;

  public CheckoutUseCaseImpl(AuthGateway authGateway, InventoryGateway inventoryGateway, PaymentGateway paymentGateway, NotificationsGateway notificationsGateway, ReceiptRepository receiptRepository) {
    this.authGateway = authGateway;
    this.inventoryGateway = inventoryGateway;
    this.paymentGateway = paymentGateway;
    this.notificationsGateway = notificationsGateway;
    this.receiptRepository = receiptRepository;
  }

  @Override
  public Receipt checkout(Order order, String authorization) {
    authGateway.authorizeUser(authorization);
    int amountToBePaid = calculateTotalCost(order);

    PaymentDetails paymentDetails = paymentGateway.makePayment(authorization, new PaymentAmount(amountToBePaid));
    order.setTransactionId(paymentDetails.getTransactionId());
    Receipt receipt = receiptRepository.saveReceipt(order);
    notificationsGateway.notify(authorization, receipt);
    return receipt;
  }

  private int calculateTotalCost(Order order) {
    List<String> outOfStock = new ArrayList<>();
    int cost = getTotalCost(order, outOfStock);
    validateNoItemsOutOfStock(outOfStock);
    return cost;
  }

  private static void validateNoItemsOutOfStock(List<String> outOfStock) {
    if (!outOfStock.isEmpty()) {
      ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, 99, "Out of stock products " + String.join(", ", outOfStock));
      throw new ServiceException(apiError);
    }
  }

  private int getTotalCost(Order order, List<String> outOfStock) {
    int cost = 0;
    for (RequestedProduct requestedProduct : order.getCart().getProducts()) {
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

