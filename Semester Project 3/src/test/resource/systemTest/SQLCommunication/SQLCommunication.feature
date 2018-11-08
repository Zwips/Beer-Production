Feature:Pseudo unit testing - The system can communicate with the SQL database

  Background: SQL module
    Given a connection to the SQL module
    Given a connection to the database

#  IBatch selectFromBatch(int batchID);
#  void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective);
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
  Scenario: Retrieving a batch log
    Given that a batchlog entry with batchid -1 exists
    When retrieving a batchlog entry with batchID -1
    Then the correct bachlog entry is retrieved

#  List<IBatchLog> getBatchLogByMachineID(String machineID);
#  void InsertIntoBatch_log(int batchID, String MachineID, int orderID);
  Scenario: Retrieving all batches by a machine
    Given that a batchlog entry with machineID -1 exists
    When retrieving a batchlog entry with machineID -1
    Then all batchlog entries for that machine is retrieved



#  void logTemperature(float value, Date timestamp, int batchID);
#  Map selectFromTemperature(String machineID, Date dateFrom);
  Scenario: Retrieving all temperatures from a machine after a date
    Given that a batchlog entry with machineID and batchID -1 exists
    And And that temperatures with batch -1 exists
    When retrieving temperatures for the machine
    Then the temperatures are correctly retrieved

#  void logVibration(float value, Date timestamp, int batchID);
#  Map selectFromVibration(String machineID, Date dateFrom);
  Scenario: Retrieving all vibrations from a machine after a date
    Given that a batchlog entry with machineID and batchID -1 exists
    And And that vibrations with batch -1 exists
    When retrieving vibrations for the machine
    Then the vibrations are correctly retrieved

#  void logHumidity(float value, Date timestamp, int batchID);
#  Map selectFromHumidity(String machineID, Date dateFrom);
  Scenario: Retrieving all humidities from a machine after a date
    Given that a batchlog entry with machineID and batchID -1 exists
    And And that humidity with batch -1 exists
    When retrieving humidity for the machine
    Then the humidity are correctly retrieved



#  void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
  Scenario: Inserting info about the rate of defective into the database
    When inserting data about the rate of defectives with machineID -1
    Then the date is correctly inserted into the database