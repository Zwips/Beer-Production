Feature: Pseudo unit testing - Writes all the possible values to the virtual simulation

  Background:
    Given A machine connection is connected to the virtual simulation

  Scenario: The amount to be produced is written to the simulation
    Given The amount to be produced is 50
    When the amount to be produced is sent to the simulation
    Then the simulation have the amount to be produced set to 50

  Scenario: The ID for the next batch is written to the simulation
    Given The batch ID for the next batch is 50
    When the ID for the next batch is sent to the simulation
    Then the simulation have the ID for the next batch set to 50

  Scenario: The machine speed is written to the simulation
    Given The machine speed to be run is 100
    When the machine speed is sent to the simulation
    Then the simulation have the machine speed set to 100

  Scenario: The product ID for the next batch is written to the simulation
    Given The product ID for the next batch is 1
    When the product ID for the next batch to be produced is sent to the simulation
    Then the simulation have the product ID for the next batch set to 1

  Scenario: The simulation receives a control command
    Given the next control command is 1 and the current state is 2
    When the control command is sent to the simulation
    Then the simulation is in a different state