Feature: Pseudo unit testing - subscribes to all the possible values in the physical simulation that change

  Background:
    Given a connection to the physical simulation

  Scenario: Successful subscription to State in  physical simulation
    Given The physical simulation is in the aborted state
    When subscribing to change in state in the physical simulation
    And the state change in the physical simulation
    Then the state change subscriber for the physical simulation is notified


  Scenario: Successful subscription to stop reason in  physical simulation
    Given The physical simulation is in the idle state
    When subscribing to the stop reason in the physical simulation
    And the physical simulation is stopped
    Then the stop reason subscriber for the physical simulation is notified