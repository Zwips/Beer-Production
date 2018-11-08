package systemTest.SQLCommunication;

import Acquantiance.IBatch;
import Acquantiance.IBatchLog;
import Acquantiance.ProductTypeEnum;
import com.prosysopc.ua.types.opcua.BuildInfoType;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.SQLCommunicationFacade;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.SetPreparedStatement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SQLCommunication {

    private ISQLCommunicationFacade sqlModule;
    private Connection connection;

    private IBatch batch;
    private int batchID;
    private int batchAmount;
    private int batchDefective;
    private ProductTypeEnum batchType;
    private Date timestamp = new Date();
    private Float temperature;
    private Float vibration;
    private Float humidity;
    private String machineID;
    private int orderID;
    private IBatchLog batchLogEntry;
    private List<IBatchLog> batchLogEntries;
    private Date measurementsTime;
    private Map<Date,Float> temperatures;
    private Map<Date, Float> vibrations;
    private Map<Date, Float> humidities;
    private int numberOfDefectives;
    private float productsInBatch;
    private float machineSpeed;
    private ProductTypeEnum productType;

    @Given("^a connection to the SQL module$")
    public void aConnectionToTheSQLModule() throws Throwable {
        this.sqlModule = new SQLCommunicationFacade();
    }

    @Given("^a connection to the database$")
    public void aConnectionToTheDatabase() throws Throwable {
        this.connection = new DatabaseConnector().OpenConnection();
    }

    @Given("^that a batch with ID -(\\d+) exists$")
    public void thatABatchWithIDExists(int batchID) throws Throwable {
        this.batchID = -batchID;
        this.batchAmount = 1;
        this.batchDefective = 1;
        this.batchType = ProductTypeEnum.ALE;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM batch WHERE batchid = ?;");
        pStatement.setInt(1,this.batchID);
        pStatement.execute();

        this.sqlModule.InsertIntoBatch(this.batchID, ProductTypeEnum.ALE, batchAmount, batchDefective);
    }

    @And("^that temperatures with batch -(\\d+) exists$")
    public void thatTemperaturesWithBatchExists(int batchID) throws Throwable {
        batchID = -batchID;
        this.temperature = 1F;
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM temperature WHERE batchid = ?;");
        pStatement.setInt(1,batchID);
        pStatement.execute();
        this.sqlModule.logTemperature(this.temperature,timestamp, batchID);
    }

    @And("^that vibrations with batch -(\\d+) exists$")
    public void thatVibrationsWithBatchExists(int batchID) throws Throwable {
        batchID = -batchID;
        this.vibration = 1F;
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM vibration WHERE batchid = ?;");
        pStatement.setInt(1,batchID);
        pStatement.execute();
        this.sqlModule.logVibration(this.vibration,timestamp, batchID);
    }

    @And("^that humidities with batch -(\\d+) exists$")
    public void thatHumiditiesWithBatchExists(int batchID) throws Throwable {
        batchID = -batchID;
        this.humidity = 1F;
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM humidity WHERE batchid = ?;");
        pStatement.setInt(1,batchID);
        pStatement.execute();
        this.sqlModule.logHumidity(this.humidity,timestamp, batchID);
    }

    @When("^retrieving a batch with ID -(\\d+)$")
    public void retrievingABatchWithID(int batchID) throws Throwable {
        batchID = -batchID;
        batch = this.sqlModule.selectFromBatch(batchID);
    }

    @Then("^the correct batch is retrieved$")
    public void theCorrectBatchIsRetrieved() throws Throwable {
        assertEquals(this.batchID, this.batch.getBatchID());
        assertEquals(this.batchAmount,this.batch.getTotalProduced());
        assertEquals(this.batchDefective,this.batch.getTotalDiscarded());
        assertEquals(this.batchType,this.batch.getProductType());

        assertEquals(this.temperature,this.batch.getTemperatures().get(this.timestamp));
        assertEquals(this.humidity,this.batch.getHumidity().get(this.timestamp));
        assertEquals(this.vibration,this.batch.getVibrations().get(this.timestamp));
    }

    @Given("^that a batchlog entry with batchid -(\\d+) exists$")
    public void thatABatchlogEntryWithBatchidExists(int batchID) throws Throwable {
        this.machineID = "-1";
        this.batchID = -batchID;
        this.orderID = -1;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM batch_log WHERE machineid = ?;");
        pStatement.setString(1,this.machineID);
        pStatement.execute();

        this.sqlModule.InsertIntoBatch_log(batchID, this.machineID,this.orderID);
    }

    @When("^retrieving a batchlog entry with batchID -(\\d+)$")
    public void retrievingABatchlogEntryWithBatchID(int batchID) throws Throwable {
        batchLogEntry = this.sqlModule.getBatchLogByBatchID(this.batchID);
    }

    @Then("^the correct bachlog entry is retrieved$")
    public void theCorrectBachlogEntryIsRetrieved() throws Throwable {
        assertEquals(this.batchID,this.batchLogEntry.getBatchID());
        assertEquals(this.orderID,this.batchLogEntry.getOrderID());
        assertEquals(this.machineID,this.batchLogEntry.getMachineID());
    }

    @Given("^that a batchlog entry with machineID -(\\d+) exists$")
    public void thatABatchlogEntryWithMachineIDExists(int machineID) throws Throwable {
        this.machineID = -machineID+"";
        this.batchID = -1;
        this.orderID = -1;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM batch_log WHERE machineid = ?;");
        pStatement.setString(1,this.machineID);
        pStatement.execute();

        this.sqlModule.InsertIntoBatch_log(batchID, this.machineID,this.orderID);
    }

    @When("^retrieving a batchlog entry with machineID -(\\d+)$")
    public void retrievingABatchlogEntryWithMachineID(int arg0) throws Throwable {
        batchLogEntries = this.sqlModule.getBatchLogByMachineID(this.machineID);
    }

    @Then("^all batchlog entries for that machine is retrieved$")
    public void allBatchlogEntriesForThatMachineIsRetrieved() throws Throwable {
        for (IBatchLog batchLogEntry: this.batchLogEntries) {
            if (batchLogEntry.getMachineID().equals(this.machineID)){
                this.batchLogEntry = batchLogEntry;
            }
        }

        assertEquals(this.batchID,this.batchLogEntry.getBatchID());
        assertEquals(this.orderID,this.batchLogEntry.getOrderID());
        assertEquals(this.machineID,this.batchLogEntry.getMachineID());
    }

    @Given("^that a batchlog entry with machineID and batchID -(\\d+) exists$")
    public void thatABatchlogEntryWithMachineIDAndBatchIDExists(int id) throws Throwable {
        this.machineID = -id+"";
        this.batchID = -id;
        this.orderID = -1;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM batch_log WHERE machineid = ?;");
        pStatement.setString(1,this.machineID);
        pStatement.execute();

        this.sqlModule.InsertIntoBatch_log(batchID, this.machineID,this.orderID);
    }

    @And("^And that temperatures with batch -(\\d+) exists$")
    public void andThatTemperaturesWithBatchExists(int batchID) throws Throwable {
        this.temperature = 1F;
        this.measurementsTime = new Date(5);
        this.batchID = -batchID;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM temperature WHERE batchid = ?;");
        pStatement.setInt(1,this.batchID);
        pStatement.execute();

        this.sqlModule.logTemperature(temperature, measurementsTime, this.batchID);
    }

    @When("^retrieving temperatures for the machine$")
    public void retrievingTemperaturesForTheMachine() throws Throwable {
        temperatures = this.sqlModule.selectFromTemperature(machineID, new Date(0));
    }

    @Then("^the temperatures are correctly retrieved$")
    public void theTemperaturesAreCorrectlyRetrieved() throws Throwable {
        Map.Entry<Date,Float> temperatureMeasurement = null;
        for (Map.Entry<Date,Float> temperatureEntry: this.temperatures.entrySet()) {
            if (temperatureEntry.getKey().equals(this.measurementsTime)){
                temperatureMeasurement = temperatureEntry;
            }
        }

        assertEquals(temperatureMeasurement.getKey(),this.measurementsTime);
        assertEquals(temperatureMeasurement.getValue(),this.temperature);
    }

    @And("^And that vibrations with batch -(\\d+) exists$")
    public void andThatVibrationsWithBatchExists(int batchID) throws Throwable {
        this.vibration = 1F;
        this.measurementsTime = new Date(5);
        this.batchID = -batchID;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM vibration WHERE batchid = ?;");
        pStatement.setInt(1,this.batchID);
        pStatement.execute();

        this.sqlModule.logVibration(vibration, measurementsTime, this.batchID);
    }

    @When("^retrieving vibrations for the machine$")
    public void retrievingVibrationsForTheMachine() throws Throwable {
        vibrations = this.sqlModule.selectFromVibration(machineID, new Date(0));
    }

    @Then("^the vibrations are correctly retrieved$")
    public void theVibrationsAreCorrectlyRetrieved() throws Throwable {
        Map.Entry<Date,Float> vibrationMeasurement = null;
        for (Map.Entry<Date,Float> vibrationEntry: this.vibrations.entrySet()) {
            if (vibrationEntry.getKey().equals(this.measurementsTime)){
                vibrationMeasurement = vibrationEntry;
            }
        }

        assertEquals(vibrationMeasurement.getKey(),this.measurementsTime);
        assertEquals(vibrationMeasurement.getValue(),this.vibration);
    }

    @And("^And that humidity with batch -(\\d+) exists$")
    public void andThatHumidityWithBatchExists(int batchID) throws Throwable {
        this.humidity = 1F;
        this.measurementsTime = new Date(5);
        this.batchID = -batchID;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM humidity WHERE batchid = ?;");
        pStatement.setInt(1,this.batchID);
        pStatement.execute();

        this.sqlModule.logHumidity(humidity, measurementsTime, this.batchID);
    }

    @When("^retrieving humidity for the machine$")
    public void retrievingHumidityForTheMachine() throws Throwable {
        humidities = this.sqlModule.selectFromHumidity(machineID, new Date(0));
    }

    @Then("^the humidity are correctly retrieved$")
    public void theHumidityAreCorrectlyRetrieved() throws Throwable {
        Map.Entry<Date,Float> humidityMeasurement = null;
        for (Map.Entry<Date,Float> humidityEntry: this.humidities.entrySet()) {
            if (humidityEntry.getKey().equals(this.measurementsTime)){
                humidityMeasurement = humidityEntry;
            }
        }

        assertEquals(humidityMeasurement.getKey(),this.measurementsTime);
        assertEquals(humidityMeasurement.getValue(),this.humidity);
    }

    @When("^inserting data about the rate of defectives with machineID -(\\d+)$")
    public void insertingDataAboutTheRateOfDefectivesWithMachineID(int machineID) throws Throwable {
        this.machineID = -machineID+"";
        this.numberOfDefectives = 1;
        this.productsInBatch = 1F;
        this.machineSpeed = 1F;
        this.productType = ProductTypeEnum.ALE;

        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM defectives WHERE machineID = ?;");
        pStatement.setString(1,this.machineID);
        pStatement.execute();

        this.sqlModule.logDefectives(this.machineID, batchDefective, productsInBatch, machineSpeed, productType);
    }

    @Then("^the date is correctly inserted into the database$")
    public void theDateIsCorrectlyInsertedIntoTheDatabase() throws Throwable {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM defectives WHERE machineid = ?");
        pStatement.setString(1,this.machineID);
        ResultSet results = pStatement.executeQuery();

        assertEquals(this.machineID,results.getString("machineid"));
        assertEquals(this.numberOfDefectives,results.getInt("numberofdefective"));
        assertEquals(this.productsInBatch,results.getFloat("productsinbatch"),0);
        assertEquals(this.machineSpeed,results.getFloat("machinespeed"),0);

        ProductTypeEnum type = ProductTypeEnum.get(results.getString("ProductType"));
        assertEquals(this.productType,type);
    }
}
