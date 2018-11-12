Feature: You can add or remove a machine from a factory

  Background: ERP module
    Given a ERP system to work on
    Given a Factory in the ERP system with the name TestFactory exists

  Scenario: Add a machine to a factory
    Given The simulation is started
    Given there is not already a machine named TestMachine
    When adding a machine named the TestMachine
    Then the machine is added

  Scenario: Removing a machine from a factory
    Given there is a machine named the TestMachine
    When removing a machine named the TestMachine
    Then the machine is removed