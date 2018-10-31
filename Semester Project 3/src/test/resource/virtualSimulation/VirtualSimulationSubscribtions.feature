Feature: Pseudo unit testing - subscribes to all the possible values in the virtual simulation that change

  Background:
    Given a connection to the virtual simulation

  Scenario: Successful subscription to State in virtual simulation
    Given The simulation in the aborted state
    When subscribing to change in state
    And the state change
    Then the state change subscriber is notified


  Scenario: Successful subscription to stop reason in virtual simulation
    Given The simulation in the idle state
    When subscribing to the stop reason
    And the simulation is stopped
    Then the stop reason subscriber is notified