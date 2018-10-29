Feature: Pseudo unit testing - reads all values from the virtual simulation

  Background:
    Given The machine connection the virtual simulation is established

  Scenario: The temperature is successfully read
    When reading the temperature
    Then the temperature is read

  Scenario: The vibration is successfully read
    When reading the vibration levels
    Then the vibration levels is read

  Scenario: The humidity is successfully read
    When reading the humidity
    Then the humidity is read

  Scenario: The current state is successfully read
    When reading the current state
    Then the current state is read

  Scenario: The current machine speed is successfully read
    When reading the the current machine speed
    Then the current machine speed is read

  Scenario: The current batch ID is successfully read
    When reading the the current batch ID
    Then the current batch ID is read

  Scenario: The amount of products in batch is successfully read
    When reading the amount of products in a batch
    Then the amount of products in a batch is read

  Scenario: The amount to be produced is successfully read
    When reading the amount to be produced
    Then the amount to be produced is read

  Scenario: The ID for the next batch is successfully read
    Given a connection to the simulation
    When reading the ID for the next batch
    Then the ID for the next batch is read

  Scenario: The machine speed is successfully read
    Given a connection to the simulation
    When reading the machine speed
    Then the machine speed is read

  Scenario: The product ID for the next batch is successfully read
    Given a connection to the simulation
    When reading product ID for the next batch
    Then the product ID for the next batch is read

  Scenario: The control command for the simulation is successfully read
    Given a connection to the simulation
    When reading control command for the simulation
    Then the control command for the simulation is read

  Scenario: The the number of defective products is successfully read
    When reading the number of defective products
    Then the the number of defective products is read

  Scenario: The the number of produced products is successfully read
    When reading the number of produced products
    Then the the number of produced products is read

  Scenario: The current product ID of the batch is successfully read
    When reading current product ID of the batch
    Then the current product ID of the batch is read

  Scenario: The stop reason ID is successfully read
    When reading the stop reason ID
    Then the stop reason ID is read

  Scenario: The stop reason value is successfully read
    When reading the stop reason value
    Then the stop reason value is read

#  subscribe to temp
#  subscribe to vibration
#  subscribe to humidity
#  subscribe to current state
#  subscribe to stop reason ID