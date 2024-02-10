package acceptance.helper;

import acceptance.SpringTestRunner;
import org.example.application.usecases.CheckoutUseCase;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UseCaseTestHelper extends SpringTestRunner implements TestHelper {
  @Autowired
  private CheckoutUseCase checkoutUseCase;

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    return checkoutUseCase.checkout(orderRequest);
  }
}
