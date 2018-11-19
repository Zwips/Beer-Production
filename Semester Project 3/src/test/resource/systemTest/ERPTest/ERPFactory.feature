Feature: Add or remove a factory from a system

  Background: having a system to work with
    Given there is an ERP system

  Scenario: Adding a factory with the name TestFactory
    Given there is no factory with the name TestFactory
    When Adding a factory with the name TestFactory
    Then The factory is added

  Scenario: Removing a factory with the name TestFactory
    Given there is a factory with the name TestFactory
    When removing a factory with name the TestFactory
    Then the factory is removed