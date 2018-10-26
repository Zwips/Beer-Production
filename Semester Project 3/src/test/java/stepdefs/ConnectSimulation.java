package stepdefs;

import communication.machineConnection.MachineConnection;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;


public class ConnectSimulation {

    private MachineConnection connection;

    @SuppressWarnings("Duplicates")
    @Given("^The simulation is started$")
    public void theSimulationIsStarted() {

        // This can't be done from Java??
        // Runtime.getRuntime().exec("cmd.exe", "/c", ".");
        File file = new File("Simulation/start.bat");
        Process process;

        try {String[] command = { "cmd.exe", "/C", "Start", file.getAbsolutePath() };
            Runtime runtime = Runtime.getRuntime();
            process  = runtime.exec(command);
            process.waitFor();

            process.destroy();
            process.destroyForcibly();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @When("^connecting the the virtual simulation$")
    public void connectingTheTheVirtualSimulation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.connection = new MachineConnection("127.0.0.1:4840","sdu","1234");

    }

    @Then("^The machine connection is connected$")
    public void theMachineConnectionIsConnected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        boolean connected = connection.isConnected();

        assertEquals(true,connected);
    }
}
