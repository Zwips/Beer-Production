package physicalSimulation;

import com.prosysopc.ua.client.UaClient;
import communication.machineConnection.Connection;
import communication.machineConnection.MachineConnection;
import communication.machineConnection.command.BatchID;
import communication.machineConnection.command.Control;
import communication.machineConnection.command.MachineSpeed;
import communication.machineConnection.command.ProductID;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertNotNull;

public class ReadAllPhysicalValues {

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

    @Given("^The machine connection the physical simulation is established$")
    public void theMachineConnectionThePhysicalSimulationIsEstablished() throws Throwable {
        this.connection = new MachineConnection("192.168.1.2:4840","sdu","1234");
    }


    @When("^reading the temperature, physical simulation$")
    public void readingTheTemperaturePhysicalSimulation() throws Throwable {
        temperature = connection.readTemperature();
    }

    @Then("^the temperature is read , physical simulation$")
    public void theTemperatureIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(temperature);
    }

    @When("^reading the vibration levels , physical simulation$")
    public void readingTheVibrationLevelsPhysicalSimulation() throws Throwable {
        vibration = connection.readVibration();
    }

    @Then("^the vibration levels is read , physical simulation$")
    public void theVibrationLevelsIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(vibration);
    }

    @When("^reading the humidity , physical simulation$")
    public void readingTheHumidityPhysicalSimulation() throws Throwable {
        humidity = connection.readHumidity();
    }

    @Then("^the humidity is read , physical simulation$")
    public void theHumidityIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(humidity);
    }

    @When("^reading the current state , physical simulation$")
    public void readingTheCurrentStatePhysicalSimulation() throws Throwable {
        currentState = connection.readCurrentState();
    }

    @Then("^the current state is read , physical simulation$")
    public void theCurrentStateIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(currentState);
    }

    @When("^reading the the current machine speed , physical simulation$")
    public void readingTheTheCurrentMachineSpeedPhysicalSimulation() throws Throwable {
        currentMachineSpeed = connection.readMachineSpeedCurrent();
    }

    @Then("^the current machine speed is read , physical simulation$")
    public void theCurrentMachineSpeedIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(currentMachineSpeed);
    }

    @When("^reading the the current batch ID , physical simulation$")
    public void readingTheTheCurrentBatchIDPhysicalSimulation() throws Throwable {
        currentBatchID = connection.readBatchIDCurrent();
    }

    @Then("^the current batch ID is read , physical simulation$")
    public void theCurrentBatchIDIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(currentBatchID);
    }

    @When("^reading the amount of products in a batch , physical simulation$")
    public void readingTheAmountOfProductsInABatchPhysicalSimulation() throws Throwable {
        amountOfProductsInBatch = connection.readProductsInBatch();
    }

    @Then("^the amount of products in a batch is read , physical simulation$")
    public void theAmountOfProductsInABatchIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(amountOfProductsInBatch);
    }

    @When("^reading the amount to be produced , physical simulation$")
    public void readingTheAmountToBeProducedPhysicalSimulation() throws Throwable {
        amountToBeProduced = connection.readProductsInBatch();
    }

    @Then("^the amount to be produced is read , physical simulation$")
    public void theAmountToBeProducedIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(amountToBeProduced);
    }

    @Given("^a connection to the simulation , physical simulation$")
    public void aConnectionToTheSimulationPhysicalSimulation() throws Throwable {
        Connection con = new Connection();
        client = con.getConnection("192.168.1.2:4840","1234","sdu");
    }

    @When("^reading the ID for the next batch , physical simulation$")
    public void readingTheIDForTheNextBatchPhysicalSimulation() throws Throwable {
        BatchID batchID = new BatchID("::Program:Cube.Command.");
        nextBatchID = batchID.readBatchIDForNextBatch(client);
        client.disconnect();
    }

    @Then("^the ID for the next batch is read , physical simulation$")
    public void theIDForTheNextBatchIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(nextBatchID);
    }

    @When("^reading the machine speed , physical simulation$")
    public void readingTheMachineSpeedPhysicalSimulation() throws Throwable {
        MachineSpeed machineSpeed = new MachineSpeed("::Program:Cube.Command.");
        nextMachineSpeed = machineSpeed.readMachineSpeed(client);
        client.disconnect();
    }

    @Then("^the machine speed is read , physical simulation$")
    public void theMachineSpeedIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(nextMachineSpeed);
    }

    @When("^reading product ID for the next batch , physical simulation$")
    public void readingProductIDForTheNextBatchPhysicalSimulation() throws Throwable {
        ProductID productID = new ProductID("::Program:Cube.Command.");
        nextProductID = productID.readProductIDForNextBatch(client);
        client.disconnect();
    }

    @Then("^the product ID for the next batch is read , physical simulation$")
    public void theProductIDForTheNextBatchIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(nextProductID);
    }

    @When("^reading control command for the simulation , physical simulation$")
    public void readingControlCommandForTheSimulationPhysicalSimulation() throws Throwable {
        Control control = new Control("::Program:Cube.Command.");
        controlCommand = control.readControlCommand(client);
        client.disconnect();
    }

    @Then("^the control command for the simulation is read , physical simulation$")
    public void theControlCommandForTheSimulationIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(controlCommand);
    }

    @When("^reading the number of defective products , physical simulation$")
    public void readingTheNumberOfDefectiveProductsPhysicalSimulation() throws Throwable {
        numberOfDefectiveProducts = connection.readNumberOfDefectiveProducts();
    }

    @Then("^the the number of defective products is read , physical simulation$")
    public void theTheNumberOfDefectiveProductsIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(numberOfDefectiveProducts);
    }

    @When("^reading the number of produced products , physical simulation$")
    public void readingTheNumberOfProducedProductsPhysicalSimulation() throws Throwable {
        numberOfProducedProducts = connection.readNumberOfProducedProducts();
    }

    @Then("^the the number of produced products is read , physical simulation$")
    public void theTheNumberOfProducedProductsIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(numberOfProducedProducts);
    }

    @When("^reading current product ID of the batch , physical simulation$")
    public void readingCurrentProductIDOfTheBatchPhysicalSimulation() throws Throwable {
        currentProductID = connection.readCurrentProductID();
    }

    @Then("^the current product ID of the batch is read , physical simulation$")
    public void theCurrentProductIDOfTheBatchIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(currentProductID);
    }

    @When("^reading the stop reason ID , physical simulation$")
    public void readingTheStopReasonIDPhysicalSimulation() throws Throwable {
        stopReasonID = connection.readStopReasonID();
    }

    @Then("^the stop reason ID is read , physical simulation$")
    public void theStopReasonIDIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(stopReasonID);
    }

    @When("^reading the stop reason value , physical simulation$")
    public void readingTheStopReasonValuePhysicalSimulation() throws Throwable {
        stopReasonValue = connection.readStopReasonValue();
    }

    @Then("^the stop reason value is read , physical simulation$")
    public void theStopReasonValueIsReadPhysicalSimulation() throws Throwable {
        connection.disconnect();
        assertNotNull(stopReasonValue);
    }
}
