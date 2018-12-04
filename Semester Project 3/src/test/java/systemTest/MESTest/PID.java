package systemTest.MESTest;

import acquantiance.ProductTypeEnum;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.mes.MESOutFacade;
import logic.mes.pid.PIDFacade;
import org.junit.Assert;
import org.junit.Test;
import systemTest.DummyClasses.DummyMachineSpecification;
import systemTest.DummyClasses.DummyStorage;
import systemTest.ERPLevelInitializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    @Then("^the product type for the PIDOrder is ([^\"]*)$")
    public void theProductTypeForThePIDOrderIs(String arg0) throws Throwable {
        ProductTypeEnum type = ProductTypeEnum.get("Pilsner");
        assertEquals(type, PIDFacade.getInstance().getOrder(dummyStorage,dummyMachineSpecification).getProductType());
    }

    @Given("^full storage$")
    public void fullStorage() throws Throwable {
        for (ProductTypeEnum value : ProductTypeEnum.values()) {
            dummyStorage.setTargetAmount(10, value);
            dummyStorage.setCurrentAmount(10, value);
        }
    }

    @Then("^the PIDorder is null$")
    public void thePIDorderIsNull() throws Throwable {
        assertNull(PIDFacade.getInstance().getOrder(dummyStorage,dummyMachineSpecification));
    }
}
