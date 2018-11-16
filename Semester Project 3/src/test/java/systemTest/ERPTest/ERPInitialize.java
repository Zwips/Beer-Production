package systemTest.ERPTest;

import Acquantiance.ProductTypeEnum;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.SQLCommunicationFacade;
import communication.SQLCommunication.inserters.MachineInserter;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;
import logic.erp.ERPOutFacade;
import logic.erp.ProductionOrder;
import systemTest.ERPLevelInitializer;
import systemTest.SQLCommunication.SQLCommunication;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class ERPInitialize {
    private ISQLCommunicationFacade sql = new SQLCommunicationFacade();

    private ERP erp;
    private int maxOrderID;

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

    @When("^an ERP system initialises$")
    public void anERPSystemInitialises() throws Throwable {
        erp = new ERP();
    }

    @Then("^nextOrderID will be int max$")
    public void nextorderidWillBeIntMax() throws Throwable {
        try{
            assertEquals(this.maxOrderID,erp.getNextOrderID());
        } finally {
            sql.deleteOrderByOrderID(Integer.MAX_VALUE);
            erp = null;
        }
    }

}
