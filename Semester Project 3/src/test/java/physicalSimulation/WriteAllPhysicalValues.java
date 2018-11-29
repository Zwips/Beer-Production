package physicalSimulation;

import com.prosysopc.ua.client.UaClient;
import communication.machineconnection.Connection;
import communication.machineconnection.MachineConnection;
import communication.machineconnection.command.Amount;
import communication.machineconnection.command.BatchID;
import communication.machineconnection.command.MachineSpeed;
import communication.machineconnection.command.ProductID;
import communication.machineconnection.status.CurrentState;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WriteAllPhysicalValues {

    MachineConnection connection;
    private float amountToBeProduced;
    private float nextBatchID;
    private float machineSpeed;
    private float nextProductID;
    private int controlCommand;
    private float stateStart;

    @Given("^A machine connection is connected to the physical simulation$")
    public void aMachineConnectionIsConnectedToThePhysicalSimulation() throws Throwable {
        this.connection = new MachineConnection("10.112.254.165","sdu","1234");
    }

    @Given("^The amount to be produced is (\\d+) , physical simulation$")
    public void theAmountToBeProducedIsPhysicalSimulation(float amount) throws Throwable {
        amountToBeProduced = amount;
    }

    @When("^the amount to be produced is sent to the simulation , physical simulation$")
    public void theAmountToBeProducedIsSentToTheSimulationPhysicalSimulation() throws Throwable {
        connection.setAmountInNextBatch(amountToBeProduced);
    }

    @Then("^the simulation have the amount to be produced set to (\\d+) , physical simulation$")
    public void theSimulationHaveTheAmountToBeProducedSetToPhysicalSimulation(float amount) throws Throwable {
        Connection con = new Connection();
        UaClient client = con.getConnection("10.112.254.165", "1234", "sdu");

        Amount batchAmount = new Amount("::Program:Cube.Command.");
        float nextAmount = batchAmount.readAmountInNextBatch(client);
        client.disconnect();

        assertEquals(amount, nextAmount, 0);
    }

    @Given("^The batch ID for the next batch is (\\d+) , physical simulation$")
    public void theBatchIDForTheNextBatchIsPhysicalSimulation(float id) throws Throwable {
        nextBatchID = id;
    }

    @When("^the ID for the next batch is sent to the simulation , physical simulation$")
    public void theIDForTheNextBatchIsSentToTheSimulationPhysicalSimulation() throws Throwable {
        connection.setBatchIDForNextBatch(nextBatchID);
    }

    @Then("^the simulation have the ID for the next batch set to (\\d+) , physical simulation$")
    public void theSimulationHaveTheIDForTheNextBatchSetToPhysicalSimulation(float id) throws Throwable {
        Connection con = new Connection();
        UaClient client = con.getConnection("10.112.254.165", "1234", "sdu");

        BatchID batchID = new BatchID("::Program:Cube.Command.");
        float nextBatchID = batchID.readBatchIDForNextBatch(client);
        client.disconnect();

        assertEquals(id, nextBatchID, 0);
    }

    @Given("^The machine speed to be run is (\\d+) , physical simulation$")
    public void theMachineSpeedToBeRunIsPhysicalSimulation(float speed) throws Throwable {
        machineSpeed = speed;
    }

    @When("^the machine speed is sent to the simulation , physical simulation$")
    public void theMachineSpeedIsSentToTheSimulationPhysicalSimulation() throws Throwable {
        connection.setMachineSpeed(machineSpeed);
    }

    @Then("^the simulation have the machine speed set to (\\d+) , physical simulation$")
    public void theSimulationHaveTheMachineSpeedSetToPhysicalSimulation(float speed) throws Throwable {
        Connection con = new Connection();
        UaClient client = con.getConnection("10.112.254.165", "1234", "sdu");

        MachineSpeed machineSpeed = new MachineSpeed("::Program:Cube.Command.");
        float nextMachineSpeed = machineSpeed.readMachineSpeed(client);
        client.disconnect();

        assertEquals(speed, nextMachineSpeed, 0);
    }

    @Given("^The product ID for the next batch is (\\d+) , physical simulation$")
    public void theProductIDForTheNextBatchIsPhysicalSimulation(float id) throws Throwable {
        nextProductID = id;
    }

    @When("^the product ID for the next batch to be produced is sent to the simulation , physical simulation$")
    public void theProductIDForTheNextBatchToBeProducedIsSentToTheSimulationPhysicalSimulation() throws Throwable {
        connection.setProductIDForNextBatch(nextProductID);
    }

    @Then("^the simulation have the product ID for the next batch set to (\\d+) , physical simulation$")
    public void theSimulationHaveTheProductIDForTheNextBatchSetToPhysicalSimulation(float id) throws Throwable {
        Connection con = new Connection();
        UaClient client = con.getConnection("10.112.254.165", "1234", "sdu");

        ProductID productID = new ProductID("::Program:Cube.Command.");
        float nextProductID = productID.readProductIDForNextBatch(client);
        client.disconnect();

        assertEquals(id, nextProductID, 0);
    }

    @Given("^the next control command is (\\d+) and the current state is (\\d+) , physical simulation$")
    public void theNextControlCommandIsAndTheCurrentStateIsPhysicalSimulation(int command, float state) throws Throwable {
        controlCommand = command;
        connection.setControlCommand(3);
        stateStart = connection.readCurrentState();
    }

    @When("^the control command is sent to the simulation , physical simulation$")
    public void theControlCommandIsSentToTheSimulationPhysicalSimulation() throws Throwable {
        connection.setControlCommand(controlCommand);
    }

    @Then("^the simulation is in a different state , physical simulation$")
    public void theSimulationIsInADifferentStatePhysicalSimulation() throws Throwable {
        Connection con = new Connection();
        UaClient client = con.getConnection("10.112.254.165", "1234", "sdu");

        CurrentState stateNode = new CurrentState("::Program:Cube.Status.");

        sleep(1000);
        float currentState = stateNode.readCurrentState(client);
        client.disconnect();

        assertNotEquals(stateStart,currentState,0.5);
    }
}
