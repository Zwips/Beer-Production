package physicalSimulation;

import communication.machineConnection.MachineConnection;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertNotNull;

public class SubscribePhysical {

    private Integer changedState;
    private MachineConnection connection;
    private Integer changedStopReason;

    @Given("^a connection to the physical simulation$")
    public void aConnectionToThePhysicalSimulation() throws Throwable {
        this.connection = new MachineConnection("10.112.254.165","sdu","1234");
    }

    @Given("^The physical simulation is in the aborted state$")
    public void thePhysicalSimulationIsInTheAbortedState() throws Throwable {
        connection.setControlCommand(4);
    }

    @When("^subscribing to change in state in the physical simulation$")
    public void subscribingToChangeInStateInThePhysicalSimulation() throws Throwable {
        connection.subscribeToCurrentState(new StateSubscriberPhysical(this));
    }

    @And("^the state change in the physical simulation$")
    public void theStateChangeInThePhysicalSimulation() throws Throwable {
        connection.setControlCommand(5);
    }

    @Then("^the state change subscriber for the physical simulation is notified$")
    public void theStateChangeSubscriberForThePhysicalSimulationIsNotified() throws Throwable {
        sleep(2000);
        assertNotNull(this.changedState);
    }

    @SuppressWarnings("Duplicates")
    @Given("^The physical simulation is in the idle state$")
    public void thePhysicalSimulationIsInTheIdleState() throws Throwable {
        connection.setControlCommand(4);
        sleep(50);
        connection.setControlCommand(5);
        sleep(50);
        connection.setControlCommand(1);
        sleep(50);
    }

    @When("^subscribing to the stop reason in the physical simulation$")
    public void subscribingToTheStopReasonInThePhysicalSimulation() throws Throwable {
        connection.subscribeToStopReasonID(new StopReasonSubscriberPhysical(this));
    }

    @And("^the physical simulation is stopped$")
    public void thePhysicalSimulationIsStopped() throws Throwable {
        connection.setControlCommand(3);
        sleep(50);
    }

    @Then("^the stop reason subscriber for the physical simulation is notified$")
    public void theStopReasonSubscriberForThePhysicalSimulationIsNotified() throws Throwable {
        sleep(2000);
        assertNotNull(this.changedStopReason);
    }

    public void setChangedState(int state) {
        this.changedState = state;
    }

    public void setChangedStopReason(int reason) {
        this.changedStopReason = reason;
    }
}
