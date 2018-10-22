Feature: Connecting to Database

  Scenario: Connection to Database
    Given Database is not connected
    When connection to the Database
    Then Database is connected