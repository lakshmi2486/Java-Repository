Feature: LoginFeature
  This feature deals with login functionality of finance application

  Scenario: Login with valid credentials
    Given Open Edge and navigate to login page finance application
    When I enter valid userId and valid password
    And I click signIn button
    Then User should be able to login successfully and navigate to home page
    Then User should select to Main Menu
    Then User should select to Employee Service page
    Then User should select to Gate Pass Request
    Then User should select to Gate Pass
    Then User should select to Gate Pass Request Page
    And User should able to select Mumbai location