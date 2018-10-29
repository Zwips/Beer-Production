package virtualSimulation;

import com.prosysopc.ua.client.UaClient;
import communication.machineConnection.Connection;
import communication.machineConnection.MachineConnection;
import communication.machineConnection.command.BatchID;
import communication.machineConnection.command.Control;
import communication.machineConnection.command.MachineSpeed;
import communication.machineConnection.command.ProductID;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class ReadAllSimulationValues {

    private MachineConnection connection;
    private Float temperature;
    private Float vibration;
    private Float humidity;
    private Float currentState;
    private Float amountOfProductsInBatch;
    private Float currentBatchID;
    private Float currentMachineSpeed;
    private Float amountToBeProduced;
    private UaClient client;
    private Integer controlCommand;
    private Float nextProductID;
    private Float nextMachineSpeed;
    private Float nextBatchID;
    private Integer stopReasonValue;
    private Integer stopReasonID;
    private Integer numberOfProducedProducts;
    private Float currentProductID;
    private Integer numberOfDefectiveProducts;

    @Given("^The machine connection the virtual simulation is established$")
    public void theMachineConnectionTheVirtualSimulationIsEstablished() throws Throwable {
        this.connection = new MachineConnection("127.0.0.1:4840","sdu","1234");
    }

    @When("^reading the temperature$")
    public void readingTheTemperature() throws Throwable {
        temperature = connection.readTemperature();
    }

    @Then("^the temperature is read$")
    public void theTemperatureIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(temperature);
    }

    @When("^reading the vibration levels$")
    public void readingTheVibrationLevels() throws Throwable {
        vibration = connection.readVibration();
    }

    @Then("^the vibration levels is read$")
    public void theVibrationLevelsIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(vibration);
    }

    @When("^reading the humidity$")
    public void readingTheHumidity() throws Throwable {
        humidity = connection.readHumidity();
    }

    @Then("^the humidity is read$")
    public void theHumidityIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(humidity);
    }

    @When("^reading the current state$")
    public void readingTheCurrentState() throws Throwable {
        currentState = connection.readCurrentState();
    }

    @Then("^the current state is read$")
    public void theCurrentStateIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(currentState);
    }

    @When("^reading the the current machine speed$")
    public void readingTheTheCurrentMachineSpeed() throws Throwable {
        currentMachineSpeed = connection.readMachineSpeedCurrent();
    }

    @Then("^the current machine speed is read$")
    public void theCurrentMachineSpeedIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(currentMachineSpeed);
    }

    @When("^reading the the current batch ID$")
    public void readingTheTheCurrentBatchID() throws Throwable {
        currentBatchID = connection.readBatchIDCurrent();
     }

    @Then("^the current batch ID is read$")
    public void theCurrentBatchIDIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(currentBatchID);
    }

    @When("^reading the amount of products in a batch$")
    public void readingTheAmountOfProductsInABatch() throws Throwable {
        amountOfProductsInBatch = connection.readProductsInBatch();
    }

    @Then("^the amount of products in a batch is read$")
    public void theAmountOfProdcutsInABatchIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(amountOfProductsInBatch);
    }

    @When("^reading the amount to be produced$")
    public void readingTheAmountToBeProduced() throws Throwable {
        amountToBeProduced = connection.readProductsInBatch();
    }

    @Then("^the amount to be produced is read$")
    public void theAmountToBeProducedIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(amountToBeProduced);
    }

    @When("^reading the ID for the next batch$")
    public void readingTheIDForTheNextBatch() throws Throwable {
        BatchID batchID = new BatchID("::Program:Cube.Command.");
        nextBatchID = batchID.readBatchIDForNextBatch(client);
        client.disconnect();
    }

    @Then("^the ID for the next batch is read$")
    public void theIDForTheNextBatchIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(nextBatchID);
    }

    @When("^reading the machine speed$")
    public void readingTheMachineSpeed() throws Throwable {
        MachineSpeed machineSpeed = new MachineSpeed("::Program:Cube.Command.");
        nextMachineSpeed = machineSpeed.readMachineSpeed(client);
        client.disconnect();
    }

    @Then("^the machine speed is read$")
    public void theMachineSpeedIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(nextMachineSpeed);
    }

    @When("^reading product ID for the next batch$")
    public void readingProductIDForTheNextBatch() throws Throwable {
        ProductID productID = new ProductID("::Program:Cube.Command.");
        nextProductID = productID.readProductIDForNextBatch(client);
        client.disconnect();
    }

    @Then("^the product ID for the next batch is read$")
    public void theProductIDForTheNextBatchIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(nextProductID);
    }

    @When("^reading control command for the simulation$")
    public void readingControlCommandForTheSimulation() throws Throwable {
        Control control = new Control("::Program:Cube.Command.");
        controlCommand = control.readControlCommand(client);
        client.disconnect();
    }

    @Then("^the control command for the simulation is read$")
    public void theControlCommandForTheSimulationIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(controlCommand);
    }

    @Given("^a connection to the simulation$")
    public void aConnectionToTheSimulation() throws Throwable {
        Connection con = new Connection();
        client = con.getConnection("127.0.0.1:4840","1234","sdu");
    }

    @When("^reading the number of defective products$")
    public void readingTheNumberOfDefectiveProducts() throws Throwable {
        numberOfDefectiveProducts = connection.readNumberOfDefectiveProducts();
    }

    @Then("^the the number of defective products is read$")
    public void theTheNumberOfDefectiveProductsIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(numberOfDefectiveProducts);
    }

    @When("^reading the number of produced products$")
    public void readingTheNumberOfProducedProducts() throws Throwable {
        numberOfProducedProducts = connection.readNumberOfProducedProducts();
    }

    @Then("^the the number of produced products is read$")
    public void theTheNumberOfProducedProductsIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(numberOfProducedProducts);
    }

    @When("^reading current product ID of the batch$")
    public void readingCurrentProductIDOfTheBatch() throws Throwable {
        currentProductID = connection.readCurrentProductID();
    }

    @Then("^the current product ID of the batch is read$")
    public void theCurrentProductIDOfTheBatchIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(currentProductID);
    }

    @When("^reading the stop reason ID$")
    public void readingTheStopReasonID() throws Throwable {
        stopReasonID = connection.readStopReasonID();
    }

    @Then("^the stop reason ID is read$")
    public void theStopReasonIDIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(stopReasonID);
    }

    @When("^reading the stop reason value$")
    public void readingTheStopReasonValue() throws Throwable {
        stopReasonValue = connection.readStopReasonValue();
    }

    @Then("^the stop reason value is read$")
    public void theStopReasonValueIsRead() throws Throwable {
        connection.disConnect();
        assertNotNull(stopReasonValue);
    }
}
