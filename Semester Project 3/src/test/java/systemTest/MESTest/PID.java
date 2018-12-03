package systemTest.MESTest;

import acquantiance.ProductTypeEnum;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.mes.MESOutFacade;
import org.junit.Assert;
import systemTest.ERPLevelInitializer;

import static org.junit.Assert.assertEquals;

public class PID {
    @Given("^the system is initialized, atleast at MES level$")
    public void theSystemIsInitializedAtleastAtMESLevel() throws Throwable {
        ERPLevelInitializer.glue();
    }

    @Given("^All types but \"([^\"]*)\" is full capacity$")
    public void allTypesButIsFullCapacity(String arg0) throws Throwable {
        ProductTypeEnum type = ProductTypeEnum.get(arg0);
        for (ProductTypeEnum value : ProductTypeEnum.values()) {
            MESOutFacade.getInstance().updateStorageCurrentAmount(100,"theplant",value);
            MESOutFacade.getInstance().updateStorageTargetAmount(100,"theplant",value);
        }
        MESOutFacade.getInstance().updateStorageCurrentAmount(0,"theplant",type);

    }

    @When("^the PID is queried for next PIDOrder$")
    public void thePIDIsQueriedForNextPIDOrder() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        //TODO her skal i selv skrive ind hvilke kald PID tager imod for at få det rigtige resultat i næste Then skridtet
        throw new PendingException();
    }

    @Then("^the product type for the PIDOrder is \"([^\"]*)\"$")
    public void theProductTypeForThePIDOrderIs(String arg0) throws Throwable {
        ProductTypeEnum type = ProductTypeEnum.get(arg0);
        //TODO IOrder order = PID.getOrder(); eller hvilket kald der skal til
        //TODO assertEquals(type, order.getProductTypeEnum); eller noget andet i den stil
    }
}
