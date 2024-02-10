package acceptance.helper;

import acceptance.SpringTestRunner;
import io.cucumber.spring.ScenarioScope;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class RestTestHelper extends SpringTestRunner implements TestHelper {

  private final String url = "/checkout";

  @Autowired
  private TestRestTemplate restTemplate;

  @Override
  public Receipt checkout(OrderRequest orderRequest) {
    HttpEntity<OrderRequest> httpEntity = new HttpEntity<>(orderRequest);
    ResponseEntity<Receipt> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Receipt.class);
    return responseEntity.getBody();
  }
}
