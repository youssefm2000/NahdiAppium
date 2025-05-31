Feature: Place an Order

  Background:
    Given the Nahdi app is launched
    When I select the language
    And I click on continue button
    And I select the country
    And clicking on continue button
    And I click on Skip button
    And I enter username
    And I click on continue
    And I click on login with password instead button
    And I enter password
    And I click on Continue to see home page


  Scenario: Place an order successfully
    When i click on cart icon
    And I select a product from cart page
    And I click on Checkout Button
    And I select a delivery slot
    And I select the available time
    And I click on Confirm button
    And scroll down to payement method
    And I select the payement method i need it
    And I click on place order button
    Then I should see the confirmation message



















  Scenario: Successfully placing an order 2
    When the user adds a product to the cart from the home page
    And the user opens the cart
    And the user selects a delivery method
    And the user proceeds to checkout
    And the user selects a delivery slot
    And the user selects a payment method
    And the user places the order
    Then a confirmation message should be displayed