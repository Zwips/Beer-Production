package systemTest.ERPTest;

import communication.CommunicationFacade;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gui.GUIOutFacade;
import logic.erp.ERP;
import logic.erp.ERPFacade;
import logic.erp.ERPOutFacade;
import logic.mes.MESFacade;
import logic.mes.MESOutFacade;
import systemTest.ERPLevelInitializer;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class ERPMachine {

    private ERP erp;

    @Given("^a ERP system to work on$")
    public void aERPSystemToWorkOn() throws Throwable {
        ERPLevelInitializer.glue();
        erp = new ERP();
    }

    @Given("^a Factory in the ERP system with the name TestFactory exists$")
    public void aFactoryInTheERPSystemWithTheNameTestFactoryExists() throws Throwable {
        erp.addProcessingPlant("TestFactory");
    }

    @SuppressWarnings("Duplicates")
    @Given("^The simulation is started$")
    public void theSimulationIsStarted() {
        File file = new File("Simulation/start.bat");
        Process process;

        try {String[] command = { "cmd.exe", "/C", "Start", file.getAbsolutePath() };
            Runtime runtime = Runtime.getRuntime();
            process  = runtime.exec(command);
            process.waitFor();

            process.destroy();
            process.destroyForcibly();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("^there is not already a machine named TestMachine$")
    public void thereIsNotAlreadyAMachineNamedTestMachine() throws Throwable {
        if(erp.checkForMachine("TestMachine")) {
            erp.removeMachine("TestFactory","TestMachine");
        }
    }

    @When("^adding a machine named the TestMachine$")
    public void addingAMachineNamedTheTestMachine() throws Throwable {
        erp.addMachine("TestFactory", "TestMachine","127.0.0.1:4840", "sdu","1234" );
    }

    @Then("^the machine is added$")
    public void theMachineIsAdded() throws Throwable {
        assertEquals(true,erp.checkForMachine("TestMachine"));
    }

    @Given("^there is a machine named the TestMachine$")
    public void thereIsAMachineNamedTheTestMachine() throws Throwable {
        if(!erp.checkForMachine("TestMachine")){
            erp.addMachine("TestFactory", "TestMachine","127.0.0.1:4840", "sdu","1234" );
        }
    }

    @When("^removing a machine named the TestMachine$")
    public void removingAMachineNamedTheTestMachine() throws Throwable {
        erp.removeMachine("TestFactory","TestMachine");
    }

    @Then("^the machine is removed$")
    public void theMachineIsRemoved() throws Throwable {
        assertEquals(false, erp.checkForMachine("TestMachine"));
    }


}
