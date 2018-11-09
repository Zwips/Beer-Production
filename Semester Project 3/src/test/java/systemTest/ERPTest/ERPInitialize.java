package systemTest.ERPTest;

import Acquantiance.ProductTypeEnum;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.SQLCommunicationFacade;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;
import logic.erp.ProductionOrder;
import systemTest.SQLCommunication.SQLCommunication;

import java.sql.PreparedStatement;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class ERPInitialize {

    ISQLCommunicationFacade sql = new SQLCommunicationFacade();
    ERP erp;

    @Given("^A batchID at int max is in the database$")
    public void aBatchIDAtIntMaxIsInTheDatabase() throws Throwable {
        sql.InsertIntoBatch_log(Integer.MAX_VALUE,"TestMachine", -1);


    }

    @When("^an ERP system initialises$")
    public void anERPSystemInitialises() throws Throwable {
        ERPMachine.testGlue();
        erp = new ERP();

    }

    @Then("^nextBatchID will be int max$")
    public void nextbatchidWillBeIntMax() throws Throwable {
        sleep(10000);
        assertEquals(Integer.MAX_VALUE, erp.getNextBatchID());
        sql.deleteBatchLogByBatchID(Integer.MAX_VALUE);
    }



    @Given("^A orderID at int max is in the database$")
    public void aOrderIDAtIntMaxIsInTheDatabase() throws Throwable {
        int orderID = Integer.MAX_VALUE;
        int amount = 1;
        ProductTypeEnum productType = ProductTypeEnum.ALE;
        Date earliestDeliveryDate = new Date(1000000);
        Date latesDeliveryDate = new Date(5000000);
        int priority = 1;
        ProductionOrder order = new ProductionOrder(amount,productType,earliestDeliveryDate,earliestDeliveryDate,priority);
        order.setOrderID(orderID);
        sql.logOrder(order);
    }

    @Then("^nextOrderID will be int max$")
    public void nextorderidWillBeIntMax() throws Throwable {
        assertEquals(Integer.MAX_VALUE,erp.getNextOrderID());
        sql.deleteOrderByOrderID(Integer.MAX_VALUE);
    }

    @When("^an ERP system initialises again$")
    public void anERPSystemInitialisesAgain() throws Throwable {
        ERPMachine.testGlue();
        erp = new ERP();
    }
}
