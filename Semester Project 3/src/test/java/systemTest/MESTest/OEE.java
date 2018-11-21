package systemTest.MESTest;

import Acquantiance.IOEE;
import communication.SQLCommunication.selecters.OEEByMachineRetriever;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.machineConnection.MachineConnection;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.mes.Machine;
import systemTest.ERPLevelInitializer;

import java.sql.Statement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertNotEquals;

public class OEE {
    Machine machine;
    String machineID ="TestMachine";
    String factoryID = "TestFactory";

    @Given("^there is a connection to a machinesimulation with the machineID TestMachine$")
    public void thereIsAConnectionToAMachinesimulationWithTheMachineIDTestMachine() throws Throwable {

        ERPLevelInitializer.glue();
        machine = new Machine(machineID,"127.0.0.1:4840","sdu","1234", factoryID );
    }

    @When("^State changes to aborted$")
    public void stateChangesToAborted() throws Throwable {
        try(MachineConnection connection = new MachineConnection("127.0.0.1:4840","sdu","1234")) {
            connection.setControlCommand(4);
            sleep(500);
            connection.setControlCommand(5);
            sleep(500);
            connection.setControlCommand(1);
            sleep(500);
        }
    }

    @And("^State changes to reset$")
    public void stateChangesToReset() throws Throwable {
        try(MachineConnection connection = new MachineConnection("127.0.0.1:4840","sdu","1234")) {
            connection.setControlCommand(4);
            sleep(500);
            connection.setControlCommand(5);
            sleep(500);
            connection.setControlCommand(1);
            sleep(500);
        }
    }

    @Then("^the state change is logged in the database$")
    public void theStateChangeIsLoggedInTheDatabase() throws Throwable {
        try {
            IOEE oee = new OEEByMachineRetriever().getOEE(machineID, factoryID);
            assertNotEquals(0, oee.getStateChangeMap().size());
        }finally {
            Statement statement= new DatabaseConnector().openConnection().createStatement();
            statement.execute("DELETE FROM oee WHERE machineid = " +"'"+ machineID + "'");
        }
    }
}
