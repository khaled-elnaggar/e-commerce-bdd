Feature: Checkout Process

  As a customer
  I want to be able to checkout after I am done shopping
  So that I can purchase my cart items

  Scenario: Successful Checkout as Signed-In Customer
    Given the customer is signed in
    And the customer has the following items in the cart:
      | Item      | Price | Quantity | In-stock |
      | Product A | 5     | 1        | 5        |
      | Product B | 3     | 3        | 3        |
    When the customer proceeds to checkout
    And the customer issues valid payment information
    Then the order receipt should be generated successfully with total price = 14
    And the customer receives a confirmation message
    And a receipt is available for printing


