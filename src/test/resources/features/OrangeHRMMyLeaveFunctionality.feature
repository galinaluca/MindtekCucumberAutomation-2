@MB2P-112
Feature: Validating OrangeHRM application MyLeave functionality
  Scenario:Validating MyLeave functionality
    Given user navigates to OrangeHRM application
    When user provides username "Admin" and password "admin123" and clicks login
    And user click on My Leave module
    And user provides dates "from" and "to" and click on search button
    Then user validates that leave with this date exists