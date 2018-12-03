package systemTest.MESTest;

import acquantiance.ProductTypeEnum;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.mes.MESOutFacade;
import logic.mes.pid.PIDFacade;
import org.junit.Assert;
import systemTest.DummyClasses.DummyMachineSpecification;
import systemTest.DummyClasses.DummyStorage;
import systemTest.ERPLevelInitializer;

import static org.junit.Assert.assertEquals;

public class PID {
    private DummyStorage dummyStorage;
    private DummyMachineSpecification dummyMachineSpecification;

    @Given("^the system is initialized, atleast at MES level$")
    public void theSystemIsInitializedAtleastAtMESLevel() throws Throwable {
        dummyMachineSpecification = new DummyMachineSpecification();
        dummyStorage = new DummyStorage();
    }

    @Given("^All types but ([^\"]*) is full capacity$")
    public void allTypesButIsFullCapacity(String arg0) throws Throwable {
        ProductTypeEnum type = ProductTypeEnum.get("Pilsner");
        for (ProductTypeEnum value : ProductTypeEnum.values()) {
            dummyStorage.setTargetAmount(10, value);
            dummyStorage.setCurrentAmount(1, value);
        }
        dummyStorage.setTargetAmount(100,type);

    }

    @When("^the PID is queried for next PIDOrder$")
    public void thePIDIsQueriedForNextPIDOrder() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        PIDFacade.getInstance().getOrder(dummyStorage,dummyMachineSpecification);
        //TODO her skal i selv skrive ind hvilke kald PID tager imod for at få det rigtige resultat i næste Then skridtet
    }

    @Then("^the product type for the PIDOrder is ([^\"]*)$")
    public void theProductTypeForThePIDOrderIs(String arg0) throws Throwable {
        ProductTypeEnum type = ProductTypeEnum.get("Pilsner");
        //TODO IOrder order = PID.getOrder(); eller hvilket kald der skal til
        assertEquals(type, PIDFacade.getInstance().getOrder(dummyStorage,dummyMachineSpecification).getProductTypeEnum());
    }
}
