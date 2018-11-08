Feature: Add or remove a factory from an ERP system

  Background: having an ERP system to work with
    Given there is an ERP system


    Scenario: Adding a factory with the name TestFactory
      When there is no factory with the name TestFactory
      And Adding a factory with the name TestFactory
      Then The factory is added

    Scenario: Removing a factory with the name TestFactory
      When there is a factory with the name TestFactory
      And removing a factory with name the TestFactory
      Then the factory is removed