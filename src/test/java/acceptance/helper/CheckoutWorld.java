package acceptance.helper;

import acceptance.model.ResponseMode;
import acceptance.model.RestRequest;
import lombok.Data;
import org.example.presentation.rest.dto.ApiError;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Data
@Component
public class CheckoutWorld {

  public static final String USER_AUTHORIZATION = "12345689";
  public static final String ORDER_ID = "orderId-123";
  public static final String TRANSACTION_ID = "9b8201fe-8330-4d95-9e7f-8877488858b3";

  private OrderRequest orderRequest;

  public CheckoutWorld() {
    this.orderRequest = new OrderRequest(ORDER_ID);
  }



  private boolean invoked;
  private RestRequest request;

  private ResponseEntity<ApiError> errorResponseInfo;
  private ResponseEntity<Receipt> successfulResponseInfo;

  private ResponseMode responseMode = ResponseMode.SUCCESSFUL;

  private static final String URL = "/checkout";

  public void initializeRequest() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, USER_AUTHORIZATION);
    HttpEntity<Object> httpEntity = new HttpEntity<>(this.orderRequest, headers);

    request = new RestRequest(URL, HttpMethod.POST, httpEntity);
  }

  public void invoke() {
    if (responseMode.equals(ResponseMode.SUCCESSFUL)) {
      this.successfulResponseInfo = request.exchange(Receipt.class);
    } else {
      this.errorResponseInfo = request.exchange(ApiError.class);
    }
  }

}
