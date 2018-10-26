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

  Scenario: The current state is successfully read
    Given The machine connection the virtual simulation is established
    When reading the current state
    Then the current state is read

  Scenario: The current machine speed is successfully read
    Given The machine connection the virtual simulation is established
    When reading the the current machine speed
    Then the current machine speed is read

  Scenario: The current batch ID is successfully read
    Given The machine connection the virtual simulation is established
    When reading the the current batch ID
    Then the current batch ID is read

  Scenario: The amount of products in batch is successfully read
    Given The machine connection the virtual simulation is established
    When reading the amount of products in a batch
    Then the amount of prodcuts in a batch is read

