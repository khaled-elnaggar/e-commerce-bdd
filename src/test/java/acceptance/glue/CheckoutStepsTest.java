package acceptance.glue;

import acceptance.SpringFitnesseTest;
import acceptance.helper.PostOrderWorld;
import acceptance.helper.datatypes.HelperProduct;
import acceptance.model.ResponseMode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.example.application.usecases.CheckoutUseCase;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;
import org.example.presentation.rest.dto.Cart;
import org.example.presentation.rest.dto.RequestedProduct;
import org.example.presentation.rest.dto.Receipt;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutStepsTest extends SpringFitnesseTest {


  @Autowired
  private PostOrderWorld postOrderWorld;

  @Autowired
  ObjectMapper objectMapper;

  @Rule
  static WireMockRule authorizationGateway = new WireMockRule(8040);
  @Rule
  static WireMockRule inventoryGateway = new WireMockRule(8050);
  @Rule
  static WireMockRule notificationsGateway = new WireMockRule(8060);
  @Rule
  static WireMockRule paymentsGateway = new WireMockRule(8070);

  @BeforeAll
  static void beforeAll() {
    authorizationGateway.start();
    inventoryGateway.start();
    notificationsGateway.start();
    paymentsGateway.start();
  }

  @AfterAll
  static void afterAll() {
    authorizationGateway.stop();
    inventoryGateway.stop();
    notificationsGateway.stop();
    paymentsGateway.stop();
  }

  @Given("the customer is signed in")
  public void theCustomerIsSignedIn() {
    //WireMock -> POST /auth -> successful 200
    authorizationGateway.stubFor(
            WireMock.post(WireMock.urlPathEqualTo("/auth"))
                    .withHeader(HttpHeaders.AUTHORIZATION, WireMock.equalTo(PostOrderWorld.USER_AUTHORIZATION))
                    .willReturn(aResponse()
                            .withStatus(HttpStatus.OK.value()))
    );
  }


  @Given("the customer has the following items in the cart:")
  @SneakyThrows
  public void theCustomerHasTheFollowingItemsInTheCart(List<HelperProduct> products) {
    Cart cart = new Cart();

    for (HelperProduct helperProduct : products) {
      ProductInfo returnedProduct = new ProductInfo(helperProduct.getId(), helperProduct.getInStock(), helperProduct.getPrice());

      inventoryGateway.stubFor(
              WireMock.get(WireMock.urlPathEqualTo("/inventory/products/" + helperProduct.getId()))
                      .willReturn(aResponse()
                              .withStatus(HttpStatus.OK.value())
                              .withBody(objectMapper.writeValueAsString(returnedProduct)))
      );


      RequestedProduct requestedProduct = new RequestedProduct(helperProduct.getId(), helperProduct.getQuantity());
      cart.getProducts().add(requestedProduct);
    }

    postOrderWorld.getOrder().setCart(cart);
  }

  @When("the customer proceeds to checkout")
  public void theCustomerProceedsToCheckout() {
    // create request object
    postOrderWorld.initializeRequest();
  }


  @And("the customer issues valid payment information")
  public void theCustomerIssuesAValidPaymentWithTheFollowingDetails() throws JsonProcessingException {
    // WireMock -> POST /payment -> successful 200
    PaymentDetails paymentDetails = new PaymentDetails(PostOrderWorld.TRANSACTION_ID);
    paymentsGateway.stubFor(
            WireMock.post(WireMock.urlPathEqualTo("/payments"))
                    .withHeader(HttpHeaders.AUTHORIZATION, WireMock.equalTo(PostOrderWorld.USER_AUTHORIZATION))
//                    .
                    .willReturn(aResponse()
                            .withStatus(HttpStatus.CREATED.value())
                            .withBody(objectMapper.writeValueAsString(paymentDetails)))
    );
  }

  @Then("the order receipt should be generated successfully with total price = {int}")
  public void theOrderReceiptShouldBeGeneratedSuccessfullyWithTotalPrice(int price) {
    postOrderWorld.setResponseMode(ResponseMode.SUCCESSFUL);
    postOrderWorld.invoke();
    ResponseEntity<Receipt> successfulResponseInfo = postOrderWorld.getSuccessfulResponseInfo();
    assertEquals(HttpStatus.CREATED, successfulResponseInfo.getStatusCode());
  }

  @Then("the customer receives a confirmation message")
  public void theCustomerReceivesAConfirmationMessage() {
    // TODO: mock notification API first before invocation
    //WireMock check that the api was called
  }

  @Then("a receipt is available for printing")
  public void aReceiptIsAvailableForPrinting() {
    // @Repository -> get receipt by order id and validate on its field
  }
}
