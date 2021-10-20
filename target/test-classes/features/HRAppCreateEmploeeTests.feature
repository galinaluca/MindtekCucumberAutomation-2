@regression @smoke @ui @MB2P-204
Feature: Create Employee functionality Validation
  Background:  First repeated steps in all scenarios
  Scenario Outline: Validating Create employee functionality with valid data
    Given  user navigates to Orange HRM application
    When user logs in with username "Mindtek" password "MindtekStudent"
    And  user clicks on Create New Employee button
    And  user creates employee with data
      | First name | <First name> |
      | Last name  | <Last name>  |
      | Department | <Department> |
      | Job title  | <Job title>  |
      | Salary     | <Salary>     |
    Then user validates that employee is in list of employee
    Examples:
      | First name | Last name | Department     | Job title                | Salary |
      | John       | Doe       | IT             | President                | 100000 |
      | Patel      | Harsh     | Administration | Administration Assistant | 100000 |
      | Kim        | Yan       | Shipping       | Shipping Clerk           | 80000  |

     #Create employee without name
  @MB2P-204-TC2
  Scenario: Validating Create employee functionality without providing  name
    And user creates employee with data
      | Department | IT        |
      | Job title  | President |
      | Salary     | 900000    |
    Then user validates error message in HR App "First name and Last name are required fields"


  Scenario: Validating Create employee functionality with negative salary
    And user creates employee with data
      | Department | IT        |
      | Job title  | President |
      | Salary     | -900000    |
    Then user validates error message in HR App "Salary can not be negative"