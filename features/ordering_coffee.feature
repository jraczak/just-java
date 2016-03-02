Feature: Ordering Coffee
  Scenario: Adding when the current quantity is 20
    Given I tap the increment button 20 times
    When I tap the increment button
    Then I should see the text "You cannot order more than 20 coffees"

  Scenario: Removing when the current quantity is 1
    Given I tap the decrement button
    Then I should see the text "You must have at least 1 coffee"
    
  Scenario: Ordering 2 coffees with whipped cream
    Given I tap the whipped cream option
    When I tap the increment button
    Then I should see the text "$12.00"
    
  Scenario: Ordering 2 coffees with chocolate
    Given I tap the chocolate option
    When I tap the increment button
    Then I should see the text "$14.00"
    
  Scenario: Ordering 2 coffees with chocolate and whipped cream
    Given I tap the whipped cream option
    Then I tap the chocolate option
    When I tap the increment button
    Then I should see the text "$16.00"
    
  Scenario: Submitting an order
    Given I enter the name "Justin" into the name field
    When I tap the increment button
    Then I scroll down
    Then I tap the order button
    Then I should see the text "Share with Inbox"
    