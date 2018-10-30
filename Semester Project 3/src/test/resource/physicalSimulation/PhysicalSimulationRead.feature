Feature: Pseudo unit testing - reads all values from the physical simulation

  Background:
    Given The machine connection the physical simulation is established

  Scenario: The temperature is successfully read , physical simulation
    When reading the temperature, physical simulation
    Then the temperature is read , physical simulation

  Scenario: The vibration is successfully read , physical simulation
    When reading the vibration levels , physical simulation
    Then the vibration levels is read , physical simulation

  Scenario: The humidity is successfully read , physical simulation
    When reading the humidity , physical simulation
    Then the humidity is read , physical simulation

  Scenario: The current state is successfully read , physical simulation
    When reading the current state , physical simulation
    Then the current state is read , physical simulation

  Scenario: The current machine speed is successfully read , physical simulation
    When reading the the current machine speed , physical simulation
    Then the current machine speed is read , physical simulation

  Scenario: The current batch ID is successfully read , physical simulation
    When reading the the current batch ID , physical simulation
    Then the current batch ID is read , physical simulation

  Scenario: The amount of products in batch is successfully read , physical simulation
    When reading the amount of products in a batch , physical simulation
    Then the amount of products in a batch is read , physical simulation

  Scenario: The amount to be produced is successfully read , physical simulation
    When reading the amount to be produced , physical simulation
    Then the amount to be produced is read , physical simulation

  Scenario: The ID for the next batch is successfully read , physical simulation
    Given a connection to the simulation , physical simulation
    When reading the ID for the next batch , physical simulation
    Then the ID for the next batch is read , physical simulation

  Scenario: The machine speed is successfully read , physical simulation
    Given a connection to the simulation , physical simulation
    When reading the machine speed , physical simulation
    Then the machine speed is read , physical simulation

  Scenario: The product ID for the next batch is successfully read , physical simulation
    Given a connection to the simulation , physical simulation
    When reading product ID for the next batch , physical simulation
    Then the product ID for the next batch is read , physical simulation

  Scenario: The control command for the simulation is successfully read , physical simulation
    Given a connection to the simulation , physical simulation
    When reading control command for the simulation , physical simulation
    Then the control command for the simulation is read , physical simulation

  Scenario: The the number of defective products is successfully read , physical simulation
    When reading the number of defective products , physical simulation
    Then the the number of defective products is read , physical simulation

  Scenario: The the number of produced products is successfully read , physical simulation
    When reading the number of produced products , physical simulation
    Then the the number of produced products is read , physical simulation

  Scenario: The current product ID of the batch is successfully read , physical simulation
    When reading current product ID of the batch , physical simulation
    Then the current product ID of the batch is read , physical simulation

  Scenario: The stop reason ID is successfully read , physical simulation
    When reading the stop reason ID , physical simulation
    Then the stop reason ID is read , physical simulation

  Scenario: The stop reason value is successfully read , physical simulation
    When reading the stop reason value , physical simulation
    Then the stop reason value is read , physical simulation

#  subscribe to temp
#  subscribe to vibration
#  subscribe to humidity
#  subscribe to current state
#  subscribe to stop reason ID