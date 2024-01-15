package acceptance.glue;

import acceptance.SpringIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps extends SpringIntegrationTest {

  @Given("the customer is signed in")
  public void theCustomerIsSignedIn() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
  }

  @Given("the customer has the following items in the cart:")
  public void theCustomerHasTheFollowingItemsInTheCart(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
//    throw new io.cucumber.java.PendingException();
  }

  @When("the customer proceeds to checkout")
  public void theCustomerProceedsToCheckout() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
  }

  @When("every item is in stock")
  public void everyItemIsInStock() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
  }

  @When("the customer issues a valid payment with the following details:")
  public void theCustomerIssuesAValidPaymentWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
//    throw new io.cucumber.java.PendingException();
  }

  @Then("the order receipt should be generated successfully")
  public void theOrderReceiptShouldBeGeneratedSuccessfully() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
  }

  @Then("the customer receives a confirmation message")
  public void theCustomerReceivesAConfirmationMessage() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
  }

  @Then("a receipt is available for printing")
  public void aReceiptIsAvailableForPrinting() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
  }
}
