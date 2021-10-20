@MB2P-221 @regression
  Feature:  My Wishlist functionality  validation on My Store Application

    Scenario:Validating My Wishlist functionality
      Given user navigates to MyStore Application
      When  user clicks on sign in button
      And   user provides logs in with Email address "jondoe@gmail.com" and password "palaloga"
      And   user clicks on MyWishlist icon
      And   user creates a wishlist name Dresses and clicks save button
      Then  user validate that wishlist is created

