@regression @ui @MB2P-120
Feature:Validating calculate and  order creation functionalities

  Scenario Outline:Validating calculate functionality
    Given user navigates to weborders application
    When user provides username "Tester" and password "test" and clicks on login
    And user clicks on Order module
    And user selects "<product>" product with <quantity> quantity
    Then  user validates total is calculated correctly for quantity <quantity>
    Examples:
      | product     | quantity |
      | MyMoney     | 1        |
      | FamilyAlbum | 2        |
      | ScreenSaver | 5        |
      | MyMoney     | 20       |
      | ScreenSaver | 50       |

  @MB2P-121
  Scenario Outline: Validating create order functionality
    Given user navigates to weborders application
    When user provides username "Tester" and password "test" and clicks on login
    And user counts number of orders in table
    And  user clicks on Order module
    And user creates order with data
      | order   | quantity   | name   | address   | city   | state   | zip   | cc   | expire date   |

      | <order> | <quantity> | <name> | <address> | <city> | <state> | <zip> | <cc> | <expire date> |

    Then user validates success message "New order has been successfully added."
    And user validates order added to List Of Orders
    Examples:
      | order       | quantity | name     | address     | city     | state    | zip   | cc        | expire date |
      | MyMoney     | 1        | John doe | 123 My Road | Chicago  | Illinois | 12345 | 123456789 | 12/21       |
      | FamilyAlbum | 5        | Patel    | 123 School  | New York | NY       | 11122 | 123123123 | 12/22       |





