package systemTest.ERPTest;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glueCode.Starter;
import logic.erp.ERP;

import static org.junit.Assert.assertEquals;

public class ERPFactory {
    private ERP erp;
    @Given("^there is an ERP system$")
    public void thereIsAnERPSystem() throws Throwable {
        ERPMachine.testGlue();
        this.erp = new ERP();

    }

    @When("^there is no factory with the name TestFactory$")
    public void thereIsNoFactoryWithTheNameTestFactory() throws Throwable {
        erp.removeProcessingPlant("TestFactory");
    }

    @And("^Adding a factory with the name TestFactory$")
    public void addingAFactoryWithTheNameTestFactory() throws Throwable {
        erp.addProcessingPlant("TestFactory");
    }

    @Then("^The factory is added$")
    public void theFactoryIsAdded() throws Throwable {
        assertEquals(true,erp.checkForProcessingPlant("TestFactory"));

    }

    @When("^there is a factory with the name TestFactory$")
    public void thereIsAFactoryWithTheNameTestFactory() throws Throwable {
        erp.addProcessingPlant("TestFactory");
    }

    @And("^removing a factory with name the TestFactory$")
    public void removingAFactoryWithNameTheTestFactory() throws Throwable {
        erp.removeProcessingPlant("TestFactory");;
    }

    @Then("^the factory is removed$")
    public void theFactoryIsRemoved() throws Throwable {
        assertEquals(false,erp.checkForProcessingPlant("TestFactory"));
    }
}
