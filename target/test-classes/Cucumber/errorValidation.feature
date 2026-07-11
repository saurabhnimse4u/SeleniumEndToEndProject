@tag
Feature: Error Validations


  @ErrorValidation
  Scenario Outline: error validation check
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 

    Examples: 
      | name                    | password         | 
      | saurabh.nq23e@gmail.com | Sau@13269216     |