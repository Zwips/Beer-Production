Feature: OEE gets logged when machine state changes
  Background: The system exists
    Given there is a connection to a machinesimulation with the machineID TestMachine


  Scenario: Successful log when state changes
    When State changes to aborted
    And State changes to reset
    Then the state change is logged in the database