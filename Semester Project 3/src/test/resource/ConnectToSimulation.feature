Feature: The system can connect to the virtual simulation of the PLC

  Scenario: Connecting to the virtual simulation
    Given The simulation is started
    When connecting the the virtual simulation
    Then The machine connection is connected

