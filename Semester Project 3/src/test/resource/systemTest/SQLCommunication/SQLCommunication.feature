Feature:Pseudo unit testing - The system can communicate with the SQL database

  Background: SQL module
    Given a connection to the SQL module
    Given a connection to the database

#  IBatch selectFromBatch(int batchID);
#  void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective);
      #temp, humidity, vibration
#  void logTemperature(float value, Date timestamp, int batchID);
#  void logVibration(float value, Date timestamp, int batchID);
#  void logHumidity(float value, Date timestamp, int batchID);
  Scenario: Retrieving a batch
    Given that a batch with ID -1 exists
    And that temperatures with batch -1 exists
    And that vibrations with batch -1 exists
    And that humidities with batch -1 exists
    When retrieving a batch with ID -1
    Then the correct batch is retrieved






#  IBatchLog getBatchLogByBatchID(int batchID);
#  void InsertIntoBatch_log(int batchID, String MachineID, int orderID);
  Scenario:
    Given
    When
    Then

#  List<IBatchLog> getBatchLogByMachineID(String machineID);
#  void InsertIntoBatch_log(int batchID, String MachineID, int orderID);
  Scenario:
    Given
    When
    Then

#  void logTemperature(float value, Date timestamp, int batchID);
#  Map selectFromTemperature(String machineID, Date dateFrom);
  Scenario:
    Given
    When
    Then

#  void logVibration(float value, Date timestamp, int batchID);
#  Map selectFromVibration(String machineID, Date dateFrom);
  Scenario:
    Given
    When
    Then

#  void logHumidity(float value, Date timestamp, int batchID);
#  Map selectFromHumidity(String machineID, Date dateFrom);
  Scenario:
    Given
    When
    Then

#  void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
  Scenario:
    Given
    When
    Then