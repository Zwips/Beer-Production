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
import systemTest.ERPLevelInitializer;
import systemTest.SQLCommunication.SQLCommunication;

import java.sql.PreparedStatement;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class ERPInitialize {

    private ISQLCommunicationFacade sql = new SQLCommunicationFacade();
    private ERP erp;
    private int maxOrderID;

    @Given("^A batchID with int max is in batch_log in the database$")
    public void aBatchIDWithIntMaxIsInBatch_logInTheDatabase() throws Throwable {
        sql.InsertIntoBatch_log(Integer.MAX_VALUE,"TestMachine", -1);
    }

    @When("^an ERP system initialises$")
    public void anERPSystemInitialises() throws Throwable {
        ERPLevelInitializer.glue();
        erp = new ERP();
    }

    @Then("^nextBatchID will be int max$")
    public void nextbatchidWillBeIntMax() throws Throwable {
        sleep(10000);   //TODO er der en grund til denne sleep??
        try{
            assertEquals(Integer.MAX_VALUE, erp.getNextBatchID());
        } finally {
            sql.deleteBatchLogByBatchID(Integer.MAX_VALUE);
        }
    }

    @Given("^A orderID with int max is in batch_log in  the database$")
    public void aOrderIDWithIntMaxIsInBatch_logInTheDatabase()  throws Throwable {
        maxOrderID = Integer.MAX_VALUE;
        int amount = 1;
        ProductTypeEnum productType = ProductTypeEnum.ALE;
        Date earliestDeliveryDate = new Date(1000000);
        Date latestDeliveryDate = new Date(5000000);
        int priority = 1;

        ProductionOrder order = new ProductionOrder(amount,productType,earliestDeliveryDate,latestDeliveryDate,priority);
        order.setOrderID(maxOrderID);
        sql.logOrder(order);
    }

    @Then("^nextOrderID will be int max$")
    public void nextorderidWillBeIntMax() throws Throwable {
        try{
            assertEquals(this.maxOrderID,erp.getNextOrderID());
        } finally {
            sql.deleteOrderByOrderID(Integer.MAX_VALUE);
        }
    }
}
