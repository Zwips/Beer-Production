package systemTest.MESTest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import systemTest.ERPLevelInitializer;

public class PID {
    @Given("^the system is initialized, atleast at MES level$")
    public void theSystemIsInitializedAtleastAtMESLevel() throws Throwable {
        ERPLevelInitializer.glue();
    }

    @Given("^All types but \"([^\"]*)\" is full capacity$")
    public void allTypesButIsFullCapacity(String arg0) throws Throwable {

    }

    @When("^the PID is queried for next PIDOrder$")
    public void thePIDIsQueriedForNextPIDOrder() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the product type for the PIDOrder is \"([^\"]*)\"$")
    public void theProductTypeForThePIDOrderIs(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
