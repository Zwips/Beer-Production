Feature: initialising the ERP

  Scenario: ERP will initialize and load factories and machines
    Given The simulation is started again
    And a Factory in the ERP system with the name TestFactory and a machine called TestMachine in the database
    When an ERP system initialises
    Then TestFactory and TestMachine will be loaded

  Scenario: ERP will initialize and update nextBatchID
    Given A batchID with int max is in batch_log in the database
    When an ERP system initialises
    Then nextBatchID will be int max

  Scenario: ERP will initialize and update nextOrderID
    Given A orderID with int max is in batch_log in  the database
    When an ERP system initialises
    Then nextOrderID will be int max


