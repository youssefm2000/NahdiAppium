Feature: Add Product to Wish List in Nahdi App

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
    Then I should see the homepage

  Scenario: Successfully adding a product to the wish list
    When the user adds a product to the wish list from the home page
    And the user opens the wish list
    Then the product should be displayed in the wish list
