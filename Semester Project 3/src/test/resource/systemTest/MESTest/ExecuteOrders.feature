Feature: Machines execute the next order in the queue

  Background: The system exists
    Given there is an ERP system, execute orders

  Scenario: A machine completes an order and the queue is not empty
    Given an order exists in the factory
    And the machine is executing an order with id -1
    When the machine completes the order
    Then the next order is executed
    And it's batch ID is 0
    And the batch log is updated
    And batch is updated in the database
    And order status is set

  Scenario: A machine completes an order and the queue is empty
    Given an order doesn't exist in the factory
    And the machine is executing an order with id -1
    When the machine completes the order
    Then the machine is added to the idle list
    And the batch log is updated
    And batch is updated in the database
    And order status is set

  Scenario: execution starts when orders are added if it is idle
    Given That a machine is not producing
    When adding an order
    And that machine recieves that order
    Then the machine executes the order

