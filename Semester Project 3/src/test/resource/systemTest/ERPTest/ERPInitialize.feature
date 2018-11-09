Feature: initialising the ERP

  Scenario: ERP will initialize and update nextBatchID
    Given A batchID at int max is in the database
    When an ERP system initialises
    Then nextBatchID will be int max

  Scenario: ERP will initialize and update nextOrderID
    Given A orderID at int max is in the database
    When an ERP system initialises again
    Then nextOrderID will be int max

