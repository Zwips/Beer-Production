Feature: You can add or remove a machine from a factory

  Background: ERP module
    Given a ERP system to work on
    Given a Factory in the ERP system with the name TestFactory exists


  Scenario: Add a machine to a factory
    Given The simulation is started
    When there is not already a machine the name TestMachine
    And adding a machine with name the TestMachine
    Then the machine is added

  Scenario: Removing a machine from a factory
    When there is a machine with name the TestMachine
    And removing a machine with name the TestMachine
    Then the machine is removed