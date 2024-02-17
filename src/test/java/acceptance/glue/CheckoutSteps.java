package acceptance.glue;

import acceptance.helper.TestHelper;
import acceptance.helper.UseCaseTestHelper;
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
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutSteps {

  private final String userAuthorizationToken = "user-id";
  private final String transactionId = "transaction-id";

  InventoryGateway inventoryGateway = Mockito.mock(InventoryGateway.class);
  PaymentGateway paymentGateway = Mockito.mock(PaymentGateway.class);
  ReceiptRepository receiptRepository = Mockito.mock(ReceiptRepository.class);

  TestHelper testHelper = new UseCaseTestHelper(inventoryGateway, paymentGateway, receiptRepository);
  OrderRequest orderRequest = new OrderRequest();
  Receipt receipt;

  @Given("the customer is signed in")
  public void theCustomerIsSignedIn() {
    orderRequest.setUserAuthorizationToken(userAuthorizationToken);
  }

  @Given("the customer has valid payment information")
  public void theCustomerHasValidPaymentInformation() {
  }

  @Given("the following items are in stock:")
  public void theFollowingItemsAreInStock(List<ProductInfo> products) {
    products.forEach(productInfo -> {
      when(inventoryGateway.getProductDetails(productInfo.getId())).thenReturn(productInfo);
    });
  }

  @Given("the customer has the following items in the cart:")
  public void theCustomerHasTheFollowingItemsInTheCart(List<RequestedProduct> requestedProducts) {
    Cart cart = new Cart();
    cart.setProducts(requestedProducts);
    orderRequest.setCart(cart);
  }

  @When("the customer proceeds to checkout")
  public void theCustomerProceedsToCheckout() {
    when(paymentGateway.makePayment(eq(userAuthorizationToken), any(PaymentAmount.class)))
            .thenReturn(new PaymentDetails(transactionId));
    receipt = testHelper.checkout(orderRequest);
  }

  @Then("the order receipt should be generated successfully with total price = {int}")
  public void theOrderReceiptShouldBeGeneratedSuccessfullyWithTotalPrice(Integer price) {
    verify(receiptRepository).saveReceipt(any(Order.class));
    assertEquals(price, receipt.getPaidAmount());
  }
}
