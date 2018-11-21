Feature: initialising the ERP

  Scenario: ERP will initialize and update nextOrderID
    Given A orderID with one below int max is in batch_log in  the database
    When an ERP system initialises
    Then nextOrderID will be int max

  Scenario: ERP initializes and orders start executing
    Given that pending orders exist in the database
    And that a machine is connnected
    When an ERP system initialises
    Then at least one machine starts executing orders




