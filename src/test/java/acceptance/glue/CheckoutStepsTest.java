package acceptance.glue;

import acceptance.helper.CheckoutWorld;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.application.gateways.AuthGateway;
import org.example.application.gateways.InventoryGateway;
import org.example.application.gateways.NotificationsGateway;
import org.example.application.gateways.PaymentGateway;
import org.example.application.gateways.repository.ReceiptRepository;
import org.example.application.usecases.CheckoutUseCase;
import org.example.application.usecases.CheckoutUseCaseImpl;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.SuccessfulPaymentDetails;
import org.example.presentation.rest.dto.*;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class CheckoutStepsTest {

  private final String userAuthorization = CheckoutWorld.USER_AUTHORIZATION;
  private final String transactionId = CheckoutWorld.TRANSACTION_ID;
  private final String orderId = CheckoutWorld.ORDER_ID;


  private OrderRequest orderRequest = new OrderRequest(orderId);
  private Receipt receipt;
  private boolean invoked = false;

  private void invoke() {
    if (invoked) return;
    invoked = true;
    receipt = checkoutUseCase.checkout(orderRequest, userAuthorization);
  }

  static CheckoutUseCase checkoutUseCase;
  static AuthGateway authGateway;
  static InventoryGateway inventoryGateway;
  static PaymentGateway paymentGateway;
  static NotificationsGateway notificationsGateway;

  static ReceiptRepository receiptRepository;

  @BeforeAll
  public static void beforeAll() {
    authGateway = Mockito.mock(AuthGateway.class);
    inventoryGateway = Mockito.mock(InventoryGateway.class);
    paymentGateway = Mockito.mock(PaymentGateway.class);
    notificationsGateway = Mockito.mock(NotificationsGateway.class);
    receiptRepository = Mockito.mock(ReceiptRepository.class);

    checkoutUseCase = new CheckoutUseCaseImpl(authGateway, inventoryGateway, paymentGateway,
            notificationsGateway, receiptRepository);
  }

  @Given("the customer is signed in")
  public void theCustomerIsSignedIn() {
    doNothing().when(authGateway).authorizeUser(userAuthorization);
  }


  @Given("the customer has the following items in the cart:")
  public void theCustomerHasTheFollowingItemsInTheCart(List<RequestedProduct> products) {
    Cart cart = new Cart();
    cart.getProducts().addAll(products);
    orderRequest.setCart(cart);
  }

  @When("the customer proceeds to checkout")
  public void theCustomerProceedsToCheckout() {
  }


  @And("the following items are in stock:")
  public void theFollowingItemsAreInStock(List<ProductInfo> products) {
    products.forEach(productInfo ->
            given(inventoryGateway.getProductDetails(productInfo.getId())).willReturn(productInfo));

  }

  @And("the customer issues valid payment information")
  public void theCustomerIssuesAValidPaymentWithTheFollowingDetails() {
    SuccessfulPaymentDetails successfulPaymentDetails = new SuccessfulPaymentDetails(transactionId);
    given(paymentGateway.makePayment(eq(userAuthorization), any(PaymentAmount.class)))
            .willReturn(successfulPaymentDetails);
  }

  @Then("the order receipt should be generated successfully with total price = {int}")
  public void theOrderReceiptShouldBeGeneratedSuccessfullyWithTotalPrice(int price) {
    given(receiptRepository.saveReceipt(any(SuccessfulOrder.class)))
            .willReturn(new Receipt(orderId, transactionId, price));
    invoke();
    assertEquals(price, receipt.getPaidAmount());
  }

  @Then("the customer receives a confirmation message")
  public void theCustomerReceivesAConfirmationMessage() {
    verify(notificationsGateway).notifyUser(eq(userAuthorization), any(Receipt.class));
  }

  @Then("a receipt is available for printing")
  public void aReceiptIsAvailableForPrinting() {
    verify(receiptRepository).saveReceipt(any(SuccessfulOrder.class));
  }
}
