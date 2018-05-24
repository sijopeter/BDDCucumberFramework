Feature: rs web home page to product filter and checkout feature
Description: To launch and test the functionality of rs web home page product filetering and checkout feature

Scenario: Launch the base url and check product filtering and then verify the checkout functionality
Given user naviates to the base url
When appication launches the rs web home page and display product serch field
Then user search for any products application should filter the products and display on the page
Then user searches for a list of products and add to the cart
Then user checkout the cart by providing the email address
Then close the application