Feature: initialising the ERP

  Scenario: ERP will initialize and update nextOrderID
    Given A orderID with int max is in batch_log in  the database
    When an ERP system initialises
    Then nextOrderID will be int max



