package systemTest.SQLCommunication;

import Acquantiance.IBatch;
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
import java.sql.SQLException;
import java.util.Date;
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

    private void temp(){

        /*Connection connection = new DatabaseConnector().OpenConnection();

        try{
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM batch WHERE batchid = ?;");

            pStatement.setInt(1,-1);

            pStatement.execute();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        }


        //////////////this.sqlModule.InsertIntoBatch(-1, ProductTypeEnum.ALE,1,1);
        try{
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO batch(BatchID, ProductType, Amount, Defective) VALUES (?,?,?,?)");

            pStatement.setInt(1,-1);
            pStatement.setString(2,"Ale");
            pStatement.setInt(3,1);
            pStatement.setInt(4,1);

            pStatement.execute();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        }

        //////////////////


        IBatch batch = this.sqlModule.selectFromBatch(-1);*/
    }
}
