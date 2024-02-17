package acceptance.helper;

import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.application.usecases.CheckoutUseCase;
import org.example.application.usecases.CheckoutUseCaseImpl;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;

public class UseCaseTestHelper implements TestHelper {
  CheckoutUseCase checkoutUseCase;

  public UseCaseTestHelper(InventoryGateway inventoryGateway, PaymentGateway paymentGateway, ReceiptRepository receiptRepository) {
    this.checkoutUseCase = new CheckoutUseCaseImpl(inventoryGateway, paymentGateway, receiptRepository);
  }

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    return checkoutUseCase.checkout(orderRequest);
  }
}
