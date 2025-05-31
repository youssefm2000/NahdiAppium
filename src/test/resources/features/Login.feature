Feature: Login Feature

  Scenario: Successful login with valid credentials
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


