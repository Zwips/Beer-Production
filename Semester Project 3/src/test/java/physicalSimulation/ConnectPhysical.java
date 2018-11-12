package physicalSimulation;

import communication.machineConnection.MachineConnection;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class ConnectPhysical {

    private MachineConnection connection;

    @Given("^The physical machine is started$")
    public void thePhysicalMachineIsStarted() throws Throwable {

        //This can't be done in Java

    }

    @When("^connecting the the physical simulation$")
    public void connectingTheThePhysicalSimulation() throws Throwable {
        this.connection = new MachineConnection("10.112.254.165","sdu","1234");
    }

    @Then("^The machine connection is connected to the physical simulation$")
    public void theMachineConnectionIsConnectedToThePhysicalSimulation() throws Throwable {
        boolean connected = connection.isConnected();

        assertEquals(true,connected);
    }
}

