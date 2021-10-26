@MB2P-260 @api @regression

Feature: Validating query parameters in GET account calls

  Scenario Outline: Validating order accounts are in right order
    Given user creates multiple accounts with api call with data
      | accountType | balance |
      | Checking    | 1000    |
      | Credit      | 2000    |
      | Savings     | 3000    |
      | College     | 10000   |
      | Mortgage    | 100000  |
    When user gets all accounts with api call in "<order>" order
    Then user validates status code 200
    And user validates that accounts are in "<order>" order
    Examples:
      | order |
      | asc   |
      | desc  |

@MB2P-261
  Scenario Outline: Validating limit query parameter in get account call
    Given user creates multiple accounts with api call with data
      | accountType | balance |
      | Checking    | 1000    |
      | Credit      | 2000    |
      | Savings     | 3000    |
      | College     | 10000   |
      | Mortgage    | 100000  |
    When user gets all accounts with api call in <limit> limit
    Then user validates status code 200
    And user validates that accounts are in <limit> limit
    Examples:
      | limit |
      | 2     |
      | 4     |
      | 10    |