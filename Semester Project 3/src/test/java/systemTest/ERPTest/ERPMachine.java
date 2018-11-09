package systemTest.ERPTest;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;

import static org.junit.Assert.assertEquals;

public class ERPMachine {

    private ERP erp;
    @Given("^a ERP system to work on$")
    public void aERPSystemToWorkOn() throws Throwable {
        erp = new ERP();
    }

    @Given("^a Factory in the ERP system with the name TestFactory exists$")
    public void aFactoryInTheERPSystemWithTheNameTestFactoryExists() throws Throwable {
       erp.addProcessingPlant("TestFactory");
    }

    @When("^there is not already a machine the name TestMachine$")
    public void thereIsNotAlreadyAMachineTheNameTestMachine() throws Throwable {
        erp.removeMachine("TestMachine");
    }

    @And("^adding a machine with name the TestMachine$")
    public void addingAMachineWithNameTheTestMachine() throws Throwable {
        erp.addMachine("TestMachine", "TestMachine","127.0.0.1:4840", "sdu","1234" );
    }

    @Then("^the machine is added$")
    public void theMachineIsAdded() throws Throwable {
        assertEquals(true,erp.checkForMachine("TestMachine"));
    }

    @When("^there is a machine with name the TestMachine$")
    public void thereIsAMachineWithNameTheTestMachine() throws Throwable {
        erp.addMachine("TestMachine", "TestMachine","127.0.0.1:4840", "sdu","1234" );
    }

    @And("^removing a machine with name the TestMachine$")
    public void removingAMachineWithNameTheTestMachine() throws Throwable {
        erp.removeMachine("TestMachine");
    }

    @Then("^the machine is removed$")
    public void theMachineIsRemoved() throws Throwable {
        assertEquals(false, erp.checkForMachine("TestMachine"));
    }
}
