package systemTest;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

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

    @Given("^the system is initialized$")
    public void theSystemIsInitialized() throws Throwable {
        ERPLevelInitializer.glue();
        erp = new ERP();
    }

    @Given("^all the parameters for an order$")
    public void allTheParametersForAnOrder() throws Throwable {
        this.amount = 100;
        this.productType = ProductTypeEnum.ALCOHOLFREE;
        this.earliestDeliveryDate = new Date(0);
        this.latestDeliveryDate = new Date();
        this.priority = 1;
    }

    @When("^adding the order to the queue$")
    public void addingTheOrderToTheQueue() throws Throwable {
        this.erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
    }

    @Then("^the order exists in the queue$")
    public void theOrderExistsInTheQueue() throws Throwable {
        List<IProductionOrder> orders = erp.getProductionOrders();

        IProductionOrder order;

        boolean correctOrder = false;
        int i = 0;

        do {
            order = erp.getOrder(i);

            if (order.getAmount() == this.amount && order.getProductType() == this.productType
                    && order.getEarliestDeliveryDate() == this.earliestDeliveryDate
                    && order.getLatestDeliveryDate() == this.latestDeliveryDate
                    && order.getPriority() == this.priority) {
                this.orderID = order.getOrderID();
                correctOrder = true;
                break;
            }
            i++;
        }while (order != null);

        assertTrue(correctOrder);
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
            pStatement.setInt(1,orderID);
            pStatement.execute();
            connection.close();
        }
    }
}
