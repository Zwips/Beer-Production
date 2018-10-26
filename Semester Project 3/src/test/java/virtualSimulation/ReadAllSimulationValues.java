package virtualSimulation;

import communication.machineConnection.MachineConnection;
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
        boolean temperatureIsRead = false;
        if (temperature != null){
            temperatureIsRead = true;
        }

        assertEquals(true, temperatureIsRead);
    }

    @When("^reading the vibration levels$")
    public void readingTheVibrationLevels() throws Throwable {
        vibration = connection.readVibration();
    }

    @Then("^the vibration levels is read$")
    public void theVibrationLevelsIsRead() throws Throwable {

        boolean vibrationIsRead = false;
        if (vibration != null){
            vibrationIsRead = true;
        }

        assertEquals(true, vibrationIsRead);
    }

    @When("^reading the humidity$")
    public void readingTheHumidity() throws Throwable {
        humidity = connection.readHumidity();
    }

    @Then("^the humidity is read$")
    public void theHumidityIsRead() throws Throwable {
        boolean humidityIsRead = false;
        if (humidity != null){
            humidityIsRead = true;
        }

        assertTrue(humidityIsRead);
    }

    @When("^reading the current state$")
    public void readingTheCurrentState() throws Throwable {
        currentState = connection.readCurrentState();
    }

    @Then("^the current state is read$")
    public void theCurrentStateIsRead() throws Throwable {
        boolean currentStateIsRead = false;
        if (currentState != null){
            currentStateIsRead = true;
        }

        assertTrue(currentStateIsRead);
    }

    @When("^reading the the current machine speed$")
    public void readingTheTheCurrentMachineSpeed() throws Throwable {
        currentMachineSpeed = connection.readMachineSpeedCurrent();
    }

    @Then("^the current machine speed is read$")
    public void theCurrentMachineSpeedIsRead() throws Throwable {
        assertNotNull(currentMachineSpeed);
    }

    @When("^reading the the current batch ID$")
    public void readingTheTheCurrentBatchID() throws Throwable {
        currentBatchID = connection.readBatchIDCurrent();
     }

    @Then("^the current batch ID is read$")
    public void theCurrentBatchIDIsRead() throws Throwable {
        assertNotNull(currentBatchID);
    }

    @When("^reading the amount of products in a batch$")
    public void readingTheAmountOfProductsInABatch() throws Throwable {
        amountOfProductsInBatch = connection.readProductsInBatch();
    }

    @Then("^the amount of prodcuts in a batch is read$")
    public void theAmountOfProdcutsInABatchIsRead() throws Throwable {
        assertNotNull(amountOfProductsInBatch);
    }
}
