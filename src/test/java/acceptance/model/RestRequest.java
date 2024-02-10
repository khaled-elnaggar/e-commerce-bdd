package acceptance.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Data
public class RestRequest {
  @Autowired
  private TestRestTemplate restTemplate;

  private String url;
  private HttpMethod method;
  private HttpEntity<Object> request;

  public RestRequest(String url, HttpMethod method, HttpEntity<Object> request) {
    this.url = url;
    this.method = method;
    this.request = request;
  }

  public <T> ResponseEntity<T> exchange(Class<T> responseType) {
    return restTemplate.exchange(url, method, request, responseType);
  }
}
