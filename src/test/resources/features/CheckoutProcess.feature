Feature: Checkout Process

  As a customer
  I want to be able to checkout after I am done shopping
  So that I can purchase my cart items

  Scenario: Successful Checkout as Signed-In Customer
    Given the customer is signed in
    And the customer has the following items in the cart:
      | Item      | Quantity |
      | Product A | 2        |
      | Product B | 1        |
    When the customer proceeds to checkout
    And every item is in stock
    And the customer issues a valid payment with the following details:
      | Card Type  | Card Number         | Expiry Date | CVV |
      | MasterCard | 1234 5678 9012 3456 | 12/23       | 123 |
    Then the order receipt should be generated successfully
    And the customer receives a confirmation message
    And a receipt is available for printing


