Feature: Connecting to beermachine

  Scenario: Connection to beermachine
    Given BeerMachine is not connected
    When connection to the beer machine
    Then beerMachine is connected