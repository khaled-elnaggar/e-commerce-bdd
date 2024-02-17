package acceptance.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.application.gateways.InventoryGateway;
import org.example.application.usecases.CheckoutUseCase;
import org.example.application.usecases.CheckoutUseCaseImpl;
import org.example.infrastructure.httpclients.inventory.ProductInfo;
import org.example.presentation.rest.dto.Cart;
import org.example.presentation.rest.dto.OrderRequest;
import org.example.presentation.rest.dto.Receipt;
import org.example.presentation.rest.dto.RequestedProduct;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CheckoutSteps {

  private final String userAuthorizationToken = "user-id";

  InventoryGateway inventoryGateway = Mockito.mock(InventoryGateway.class);
  CheckoutUseCase checkoutUseCase = new CheckoutUseCaseImpl(inventoryGateway);


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
    receipt = checkoutUseCase.checkout(orderRequest);
  }

  @Then("the order receipt should be generated successfully with total price = {int}")
  public void theOrderReceiptShouldBeGeneratedSuccessfullyWithTotalPrice(Integer price) {
    assertEquals(price, receipt.getPaidAmount());
  }
}
