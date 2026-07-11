
@tag
Feature: Purchase the Item from Ecommerce site

Background: 
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test to submit the order
    Given Logged in with username <name> and password <password>
    When I add the prodcut <productName> to cart
    And Checkout <productName> and submit
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name                | password       | productName |
      | saurabh.n@gmail.com | Sau@169216     | ZARA COAT 3 |