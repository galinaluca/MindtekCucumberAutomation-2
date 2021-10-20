@MB2P-200
Feature: Validating delete/edit functionalities in WebOrders application

  Scenario: Validating delete selected order functionality in View All Orders
    Given user navigates to weborders application
    When user provides username "Tester" and password "test" and clicks on login
    And user selects any order from View All Orders
    And user deletes selected order
    Then user validates that order is deleted