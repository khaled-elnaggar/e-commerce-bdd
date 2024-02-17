package acceptance.helper;

import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;

public interface TestHelper {
  Receipt checkout(OrderRequest orderRequest);
}
