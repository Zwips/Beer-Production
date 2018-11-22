Feature: Creating batch reports

  Scenario: The machine completes and order and a batch report is generated
    Given the machine is executing an order
    When the machine completes an order
    Then a batch report is created

