Feature: Machines execute the next order in the queue

  Background: The system exists
    Given there is an ERP system, execute orders

  Scenario: A machine completes an order
    Given an order exists in the factory
    And the machine is executing an order
    When the machine completes the order
    Then the next order is executed
    And the batch log is updated??