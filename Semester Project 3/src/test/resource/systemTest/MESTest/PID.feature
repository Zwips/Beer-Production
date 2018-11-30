Feature: PID gives PidOrder when system is idle and the processingplants storage capacity is not full
  Background:
    Given the system is initialized, atleast at MES level


  Scenario: PID is queried for next PIDOrder
    Given All types but "<input>" is full capacity
    When the PID is queried for next PIDOrder
    Then the product type for the PIDOrder is "<output>"


    #TODO

    Examples
    | input           | output |
    | "Pilsner"       | "Pilsner" |
    | "Ale"           | "Ale" |
    | "Wheat"         | "Wheat" |
    | "Stout"         | "Stout" |
    | "Ipa"           | "Ipa" |
    | "Alcohol Free"  | "Alcohol Free" |
