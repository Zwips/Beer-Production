package virtualSimulation;

import communication.machineconnection.MachineConnection;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertNotNull;

public class SubscribeSimulation {

    private Integer changedState;
    private MachineConnection connection;
    private Integer changedStopReason;

    @Given("^a connection to the virtual simulation$")
    public void aConnectionToTheVirtualSimulation() throws Throwable {
        this.connection = new MachineConnection("127.0.0.1:4840","sdu","1234");
    }

    @Given("^The simulation in the aborted state$")
    public void theSimulationInTheAbortedState() throws Throwable {
        connection.setControlCommand(4);
    }

    @When("^subscribing to change in state$")
    public void subscribingToChangeInState() throws Throwable {
        connection.subscribeToCurrentState(new StateSubscriber(this));
    }

    @And("^the state change$")
    public void theStateChange() throws Throwable {
        connection.setControlCommand(5);
    }

    @Then("^the state change subscriber is notified$")
    public void theStateChangeSubscriberIsNotified() throws Throwable {
        sleep(2000);
        assertNotNull(this.changedState);
    }

    @SuppressWarnings("Duplicates")
    @Given("^The simulation in the idle state$")
    public void theSimulationInTheIdleState() throws Throwable {
        connection.setControlCommand(4);
        sleep(50);
        connection.setControlCommand(5);
        sleep(50);
        connection.setControlCommand(1);
        sleep(50);
    }

    @When("^subscribing to the stop reason$")
    public void subscribingToTheStopReason() throws Throwable {
        connection.subscribeToStopReasonID(new StopReasonSubscriber(this));
    }

    @And("^the simulation is stopped$")
    public void theSimulationIsStopped() throws Throwable {
        connection.setControlCommand(3);
        sleep(50);
    }

    @Then("^the stop reason subscriber is notified$")
    public void theStopReasonSubscriberIsNotified() throws Throwable {
        sleep(2000);
        assertNotNull(this.changedStopReason);
    }

    public void setChangedState(int state){
        this.changedState = state;
    }

    public void setChangedStopReason(int reason){
        this.changedStopReason = reason;
    }
}
