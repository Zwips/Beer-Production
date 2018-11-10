Feature: Changing pending orders

  Background:
    Given a connection to the database, OrderChange
    And the system is initialized, at ERP level

  Scenario: There is no production orders in the queue
    Given there are no production orders in the queue
    And the production order information is valid
    When updating the order
    Then the updated order is not in the queue
    And the updated order is not in the database

  Scenario: There are production orders in the queue, and the update is allowed
    Given there is a production order with id 1 in the queue
    And the production order information is valid
    When updating the order
    Then the updated order is in the queue
    And the updated order is in the database

  Scenario: There are production orders in the queue, and the update is not allowed
    Given there is a production order with id 1 in the queue
    And the production order information is invalid
    When updating the order
    Then the updated order is not in the queue
    And the updated order is not in the database