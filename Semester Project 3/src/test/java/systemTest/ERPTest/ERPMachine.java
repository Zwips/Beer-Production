package systemTest.ERPTest;

import communication.CommunicationFacade;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glueCode.Starter;
import gui.GUIOutFacade;
import logic.erp.ERP;
import logic.erp.ERPFacade;
import logic.erp.ERPOutFacade;
import logic.mes.MESFacade;
import logic.mes.MESOutFacade;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class ERPMachine {

    private ERP erp;

    @Given("^a ERP system to work on$")
    public void aERPSystemToWorkOn() throws Throwable {
        testGlue();
        erp = new ERP();
    }

    @Given("^a Factory in the ERP system with the name TestFactory exists$")
    public void aFactoryInTheERPSystemWithTheNameTestFactoryExists() throws Throwable {
        erp.addProcessingPlant("TestFactory");
    }

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

    @When("^there is not already a machine the name TestMachine$")
    public void thereIsNotAlreadyAMachineTheNameTestMachine() throws Throwable {
        if(erp.checkForMachine("TestMachine")) {
            erp.removeMachine("TestFactory","TestMachine");
        }
    }

    @And("^adding a machine with name the TestMachine$")
    public void addingAMachineWithNameTheTestMachine() throws Throwable {
        erp.addMachine("TestFactory", "TestMachine","127.0.0.1:4840", "sdu","1234" );
    }

    @Then("^the machine is added$")
    public void theMachineIsAdded() throws Throwable {
        assertEquals(true,erp.checkForMachine("TestMachine"));
    }

    @When("^there is a machine with name the TestMachine$")
    public void thereIsAMachineWithNameTheTestMachine() throws Throwable {
        if(!erp.checkForMachine("TestMachine"))
        erp.addMachine("TestFactory", "TestMachine","127.0.0.1:4840", "sdu","1234" );
    }

    @And("^removing a machine with name the TestMachine$")
    public void removingAMachineWithNameTheTestMachine() throws Throwable {
        erp.removeMachine("TestFactory","TestMachine");
    }

    @Then("^the machine is removed$")
    public void theMachineIsRemoved() throws Throwable {
        assertEquals(false, erp.checkForMachine("TestMachine"));
    }

    static void testGlue(){

        ERPOutFacade erpOutFacade = ERPOutFacade.getInstance();
        MESOutFacade mesOutFacade = MESOutFacade.getInstance();
        GUIOutFacade guiOutFacade = GUIOutFacade.getInstance();

        CommunicationFacade communicationFacade = new CommunicationFacade();
        mesOutFacade.injectCommunicationFacade(communicationFacade);
        MESFacade mesFacade = new MESFacade();
        erpOutFacade.injectCommunicationFacade(mesFacade);

        ERPFacade erpFacade = new ERPFacade();
        guiOutFacade.injectCommunicationFacade(erpFacade);

    }
}
