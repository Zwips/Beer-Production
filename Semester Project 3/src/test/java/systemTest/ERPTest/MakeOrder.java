package systemTest.ERPTest;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;
import logic.erp.ERPOutFacade;
import org.junit.Assert;
import systemTest.ERPLevelInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MakeOrder {
    private ERP erp;
    private int amount;
    private ProductTypeEnum productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;
    private int orderID;
    private HashSet<Integer> orderIDsToBeRemoved;

    @Given("^the system is initialized$")
    public void theSystemIsInitialized() throws Throwable {
        ERPLevelInitializer.glue();
        erp = new ERP();
    }

    @Given("^all the parameters for an order$")
    public void allTheParametersForAnOrder() throws Throwable {
        this.amount = 10000;
        this.productType = ProductTypeEnum.ALCOHOLFREE;
        this.earliestDeliveryDate = new Date(0);
        this.latestDeliveryDate = new Date();
        this.priority = 1;
    }

    @When("^adding the order to the queue$")
    public void addingTheOrderToTheQueue() throws Throwable {
 //       this.orderID = ERPOutFacade.getInstance().getNextOrderID()+2;
        orderIDsToBeRemoved = new HashSet<>();
        orderIDsToBeRemoved.add(this.erp.getNextOrderID());
        this.erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        orderIDsToBeRemoved.add(this.erp.getNextOrderID());
        this.erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        this.orderID = this.erp.getNextOrderID();
        orderIDsToBeRemoved.add(orderID);
        this.erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
    }

    @Then("^the order exists in the queue$")
    public void theOrderExistsInTheQueue() throws Throwable {
        try{
            IProductionOrder order;

            boolean correctOrder = false;

            order = erp.getOrder(this.orderID);
            System.out.println("MakeOrder order: " + order);

            if (order != null) {
                if (order.getAmount() == this.amount && order.getProductType() == this.productType
                        && order.getEarliestDeliveryDate() == this.earliestDeliveryDate
                        && order.getLatestDeliveryDate() == this.latestDeliveryDate
                        && order.getPriority() == this.priority) {
                    this.orderID = order.getOrderID();
                    correctOrder = true;
                }
            }

            assertTrue(correctOrder);
        }catch (Exception| AssertionError e){
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            for (Integer ID : this.orderIDsToBeRemoved) {
                pStatement.setInt(1, ID);
                pStatement.execute();
            }
            connection.close();
            throw e;
        }
    }

    @And("^the order exists in the database$")
    public void theOrderExistsInTheDatabase() throws Throwable {
        Connection connection = new DatabaseConnector().openConnection();
        try{
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders WHERE orderid = ?");
            pStatement.setInt(1,this.orderID);
            ResultSet results = pStatement.executeQuery();

            results.next();

            assertEquals(amount,results.getInt("amount"));
            assertEquals(priority,results.getInt("priority"));
            assertFalse(results.getBoolean("status"));
            assertEquals(earliestDeliveryDate,new Date(results.getTimestamp("earliestdeliverydate").getTime()));
            assertEquals(latestDeliveryDate,new Date(results.getTimestamp("latestdeliverydate").getTime()));
            assertEquals(orderID,results.getInt("orderid"));
            assertEquals(productType,ProductTypeEnum.get(results.getString("producttype")));
        }   finally {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            for (Integer ID : this.orderIDsToBeRemoved) {
                pStatement.setInt(1, ID);
                pStatement.execute();
            }
            connection.close();
        }
    }
}
