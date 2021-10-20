@regression @ui @MB2P-114
Feature: Validating etsy titles


  Scenario Outline: Validating etsy separate page titles
    Given user navigates to Etsy application
    When user clicks on "<Section>" section
    Then user validates title is "<Title>"
    Examples:
      | Title                         | Section                  |
      | Jewelry & Accessories \| Etsy | Jewelery and Accessories |
      | Clothing & Shoes \| Etsy      | Clothing & Shoes         |
      | Home & Living \| Etsy         | Home & Living            |
      | Wedding & Party \| Etsy       | Wedding & Party          |
      | Toys & Entertainment \| Etsy  | Toys & Entertainment     |
      | Art & Collectibles \| Etsy    | Art & Collectibles       |