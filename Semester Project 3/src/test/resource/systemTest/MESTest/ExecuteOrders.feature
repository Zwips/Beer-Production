Feature: Machines execute the next order in the queue

  Background: The system exists
    Given there is an ERP system, execute orders

  Scenario: A machine completes an order
    Given an order exists in the factory
    And the machine is executing an order with id -1
    When the machine completes the order
    Then the next order is executed
    And it's batch ID is 0
    And the batch log is updated
    And batch is updated in the database
