@HR-20 @regression @smoke
Feature: Validate create employee functionality
  Scenario: Validate create an employee persisted in database
    Given new employee created
    When user accesses the database
    Then user validates new employee
