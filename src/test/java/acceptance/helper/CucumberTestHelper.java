package acceptance.helper;

import acceptance.SpringTestRunner;
import acceptance.glue.World;
import org.example.application.usecases.CheckoutUseCase;
import org.example.application.usecases.CheckoutUseCaseImpl;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;

public class CucumberTestHelper extends SpringTestRunner implements TestHelper {
  private CheckoutUseCase checkoutUseCase;

  private void initializeUseCase() {
    checkoutUseCase = new
            CheckoutUseCaseImpl(World.inventoryGateway, World.paymentGateway, World.receiptRepository);
  }

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    initializeUseCase();
    return checkoutUseCase.checkout(orderRequest);
  }
}
