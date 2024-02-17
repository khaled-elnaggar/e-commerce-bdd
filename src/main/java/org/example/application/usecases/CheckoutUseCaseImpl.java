package org.example.application.usecases;

import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;
import org.example.presentation.rest.dto.Order;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import org.example.presentation.rest.dto.RequestedProduct;

import java.util.List;
import java.util.stream.Collectors;

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
    List<ProductInfo> inventoryProducts = getInventoryProducts(orderRequest.getCart().getProducts());
    int cost = calculateTotalCost(inventoryProducts, orderRequest.getCart().getProducts());
    PaymentDetails paymentDetails = paymentGateway.makePayment(orderRequest.getUserAuthorizationToken(), new PaymentAmount(cost));
    receiptRepository.saveReceipt(new Order(paymentDetails, cost));
    return new Receipt(cost);
  }

  private List<ProductInfo> getInventoryProducts(List<RequestedProduct> products) {
    return products.stream()
            .map(requestedProduct -> inventoryGateway.getProductDetails(requestedProduct.getId()))
            .collect(Collectors.toList());

  }

  private int calculateTotalCost(List<ProductInfo> inventoryProducts, List<RequestedProduct> requestedProducts) {
    int cost = 0;
    for (RequestedProduct requestedProduct : requestedProducts) {
      ProductInfo productInfo = inventoryProducts.stream()
              .filter(product -> requestedProduct.getId().equals(product.getId()))
              .findFirst()
              .orElseThrow();
      cost += requestedProduct.getQuantity() * productInfo.getPrice();
    }
    return cost;
  }
}
