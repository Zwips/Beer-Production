Feature: The system can connect to the physical simulation of the PLC

  Scenario: Connecting to the physical simulation
    Given The physical machine is started
    When connecting the the physical simulation
    Then The machine connection is connected to the physical simulation