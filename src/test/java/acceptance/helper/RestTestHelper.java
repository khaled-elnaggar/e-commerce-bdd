package acceptance.helper;

import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import acceptance.SpringTestRunner;
public class RestTestHelper extends SpringTestRunner implements TestHelper{

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    return null;
  }
}
