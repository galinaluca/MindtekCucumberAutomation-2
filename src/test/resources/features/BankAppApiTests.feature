@api @MB2P-205 @regression @smoke
  Feature: Validating Bank Rest API calls

    Scenario: Validating account api calls
      Given user creates account with api call with data
      |accountType|Checking|
      |balance    |100000  |
      Then user validates status code 201
      And user validates that account is created with api get call
      When user updates account with  api call with data
        |accountType|Checking|
        |balance    |120000  |
      Then user validates status code 200
      And user validates that account is updated with api get call
      When user deletes account with api call
      Then user validates status code 200
      And user validates that account is deleted with api get call