package acceptance.glue;

import acceptance.helper.CucumberTestHelper;
import acceptance.helper.RestTestHelper;
import acceptance.helper.TestHelper;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.PaymentDetails;
import org.example.presentation.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CheckoutStepsTest {

  private final String authorizationToken = "12345689";
  private final String transactionId = "9b8201fe-8330-4d95-9e7f-8877488858b3";

  private final OrderRequest orderRequest = new OrderRequest();
  private Receipt receipt;

  private static TestHelper testHelper;


  @Autowired
  RestTestHelper restTestHelper;

  @Autowired
  CucumberTestHelper cucumberTestHelper;


  @Autowired
  private InventoryGateway inventoryGateway;
  @Autowired
  private PaymentGateway paymentGateway;
  @Autowired
  private ReceiptRepository receiptRepository;


  @BeforeAll
  public static void beforeAll() {
    System.out.println("Hello");
//    testHelper = restTestHelper;
  }

  @Before
  public void before() {
    testHelper = cucumberTestHelper;
//    inventoryGateway = Mockito.mock(InventoryGateway.class);
//    paymentGateway = Mockito.mock(PaymentGateway.class);
//    receiptRepository = Mockito.mock(ReceiptRepository.class);
  }

  @Given("the customer is signed in")
  public void theCustomerIsSignedIn() {
    orderRequest.setUserAuthorizationToken(authorizationToken);
  }


  @And("the customer has valid payment information")
  public void theCustomerIssuesAValidPaymentWithTheFollowingDetails() {
  }

  @And("the following items are in stock:")
  public void theFollowingItemsAreInStock(List<ProductInfo> products) {
    products.forEach(productInfo ->
            given(inventoryGateway.getProductDetails(productInfo.getId())).willReturn(productInfo));
  }

  @And("the customer has the following items in the cart:")
  public void theCustomerHasTheFollowingItemsInTheCart(List<RequestedProduct> products) {
    Cart cart = new Cart();
    cart.getProducts().addAll(products);
    orderRequest.setCart(cart);
  }

  @When("the customer proceeds to checkout")
  public void theCustomerProceedsToCheckout() {
    PaymentDetails paymentDetails = new PaymentDetails(transactionId);
    given(paymentGateway.makePayment(eq(authorizationToken), any(PaymentAmount.class)))
            .willReturn(paymentDetails);

    receipt = testHelper.checkout(orderRequest);
  }

  @Then("the order receipt should be generated successfully with total price = {int}")
  public void theOrderReceiptShouldBeGeneratedSuccessfullyWithTotalPrice(int price) {
    Order expectedOrder = new Order(new PaymentDetails(transactionId), price);
    verify(receiptRepository).saveReceipt(expectedOrder);

    assertEquals(price, receipt.getPaidAmount());
  }
}

