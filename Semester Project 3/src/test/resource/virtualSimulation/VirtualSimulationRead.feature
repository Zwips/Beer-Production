Feature: Reads all values from the virtual simulation

  Scenario: The temperature is successfully read
    Given The machine connection the virtual simulation is established
    When reading the temperature
    Then the temperature is read

  Scenario: The vibration is successfully read
    Given The machine connection the virtual simulation is established
    When reading the vibration levels
    Then the vibration levels is read

  Scenario: The humidity is successfully read
    Given The machine connection the virtual simulation is established
    When reading the humidity
    Then the humidity is read