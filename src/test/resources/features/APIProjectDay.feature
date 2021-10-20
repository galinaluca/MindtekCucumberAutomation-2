@HR-2001, @regression, @smoke
  Feature:  Validating employee with database employee data

Scenario Outline: Validating employee with database employee data

Given user navigates to HR Application
When user logs in with username “Mindtek” and password “MindtekStudent”
And user search for employee with employee id <employeeId>
Then user validates that employee data in UI matches with Database data
Examples:
|employeeId|
|100              |
|200              |
|206              |
Scenario: Validating employee is updated in database

Given user navigates to https://qeenv-webhr-arslan.herokuapp.com/
When user logs in with username “Mindtek” and password “MindtekStudent”
And user clicks on edit button for employees with employee id 102
And user updates first name of employee
Then user validates the name is updated in homepage
And user validates that employee first name is updated in Database

Scenario: Validating employee is deleted from database
Given user navigates to https://qeenv-webhr-arslan.herokuapp.com/
When user logs in with username “Mindtek” and password “MindtekStudent”
And user deletes employee with employee id 195
Then user validates that employee is not displayed in homepage
And user validates that employee is deleted from database
