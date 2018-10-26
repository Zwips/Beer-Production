package virtualSimulation;

import communication.machineConnection.MachineConnection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class ReadAllSimulationValues {

    private MachineConnection connection;
    private Float temperature;
    private Float vibration;
    private Float humidity;

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

        assertEquals(true, humidityIsRead);
    }
}
