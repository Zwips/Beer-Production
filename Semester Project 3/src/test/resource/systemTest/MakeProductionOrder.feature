Feature: The user can make a production order and save it in the queue

  Background: The system should exist
    Given the system is initialized

  Scenario: The user successfully adds an order to the queue
    Given all the parameters for an order
    When adding the order to the queue
    Then the order exists in the queue

