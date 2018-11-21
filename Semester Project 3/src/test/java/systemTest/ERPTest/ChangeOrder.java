package systemTest.ERPTest;

import Acquantiance.IERPFacade;
import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERPOutFacade;
import systemTest.ERPLevelInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ChangeOrder {

    private Connection connection;
    private IERPFacade erpFacade;
    private int amount;
    private ProductTypeEnum productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;
    private int orderID;
    private boolean status;
    private HashSet<Integer> orderIDsToBeRemoved = new HashSet<>();

    @Given("^a connection to the database, OrderChange$")
    public void aConnectionToTheDatabaseOrderChange() throws Throwable {
        this.connection = new DatabaseConnector().openConnection();
    }

    @And("^the system is initialized, at ERP level$")
    public void theSystemIsInitializedAtERPLevel() throws Throwable {
        erpFacade = ERPLevelInitializer.glue();
    }

    @Given("^there are no production orders in the queue$")
    public void thereAreNoProductionOrdersInTheQueue() throws Throwable {
        assertEquals(0,erpFacade.getProductionOrderQueue().size());
    }

    @And("^the production order information is valid$")
    public void theProductionOrderInformationIsValid() throws Throwable {
        amount = 1;
        productType = ProductTypeEnum.ALE;
        earliestDeliveryDate = new Date(new Date().getTime()+500000);
        latestDeliveryDate = new Date(new Date().getTime()+5000000);
        priority=1;
        orderID = ERPOutFacade.getInstance().getNextOrderID()-1;
        status=false;
    }

    @When("^updating the order$")
    public void updatingTheOrder() throws Throwable {
        this.erpFacade.updateOrder(amount,productType,earliestDeliveryDate,latestDeliveryDate,priority,orderID);
    }

    @Then("^the updated order is not in the queue$")
    public void theUpdatedOrderIsNotInTheQueue() throws Throwable {
        boolean exists = false;
        for (IProductionOrder order : this.erpFacade.getProductionOrderQueue()) {
            if(order.getOrderID() == this.orderID){

                exists = true;

                if (amount != order.getAmount()){
                    exists = false;
                }

                if (priority != order.getPriority()){
                    exists = false;
                }

                if (status != order.getStatus()){
                    exists = false;
                }

                if (!earliestDeliveryDate.equals(order.getEarliestDeliveryDate())){
                    exists = false;
                }

                if (!latestDeliveryDate.equals(order.getLatestDeliveryDate())){
                    exists = false;
                }
                if (!productType.equals(order.getProductType())){
                    exists = false;
                }
            }
        }

        assertFalse(exists);
    }

    @And("^the updated order is not in the database$")
    public void theUpdatedOrderIsNotInTheDatabase() throws Throwable {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders WHERE orderid = ?");

            pStatement.setInt(1, this.orderID);
            ResultSet results = pStatement.executeQuery();

            boolean empty = !results.isBeforeFirst();

            if (empty) {
                assertTrue(empty);
            } else {
                results.next();

                assertEquals(orderID, results.getInt("orderid"));

                boolean different = false;
                if (amount != results.getInt("amount")) {
                    different = true;
                }

                if (priority != results.getInt("priority")) {
                    different = true;
                }

                if (status != results.getBoolean("status")) {
                    different = true;
                }

                if (!earliestDeliveryDate.equals(results.getDate("earliestdeliverydate"))) {
                    different = true;
                }

                if (!latestDeliveryDate.equals(results.getDate("latestdeliverydate"))) {
                    different = true;
                }
                if (!productType.equals(ProductTypeEnum.get(results.getString("producttype")))) {
                    different = true;
                }
                assertTrue(different);
            }
        } finally {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            for (Integer ID : this.orderIDsToBeRemoved) {
                pStatement.setInt(1,ID);
                pStatement.execute();
            }
            connection.close();
        }
    }

    @Given("^there is a production order with id (\\d+) in the queue$")
    public void thereIsAProductionOrderWithIdInTheQueue(int orderID) throws Throwable {
        orderIDsToBeRemoved = new HashSet<>();
        orderIDsToBeRemoved.add(this.erpFacade.getNextOrderID());
        this.erpFacade.addOrder(10000, ProductTypeEnum.IPA, new Date(0), new Date(), 2);
        orderIDsToBeRemoved.add(this.erpFacade.getNextOrderID());
        this.erpFacade.addOrder(10000, ProductTypeEnum.IPA, new Date(0), new Date(), 2);
        orderIDsToBeRemoved.add(this.erpFacade.getNextOrderID());
        this.erpFacade.addOrder(10000, ProductTypeEnum.IPA, new Date(0), new Date(), 2);
    }

    @Then("^the updated order is in the queue$")
    public void theUpdatedOrderIsInTheQueue() throws Throwable {
        boolean orderFound = false;
        sleep(300);
        IProductionOrder order = this.erpFacade.getOrder(this.orderID);

        if (order.getOrderID() == orderID){
            assertEquals(orderID, order.getOrderID());
            assertEquals(amount,order.getAmount());
            assertEquals(priority,order.getPriority());
            assertEquals(status,order.getStatus());
            assertEquals(earliestDeliveryDate,order.getEarliestDeliveryDate());
            assertEquals(latestDeliveryDate,order.getLatestDeliveryDate());
            assertEquals(productType,order.getProductType());
            orderFound = true;
        }

        if (!orderFound){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            for (Integer ID : this.orderIDsToBeRemoved) {
                pStatement.setInt(1, ID);
                pStatement.execute();
            }
            connection.close();
            fail();
        }
    }

    @And("^the updated order is in the database$")
    public void theUpdatedOrderIsInTheDatabase() throws Throwable {
        try{
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders WHERE orderid = ?");
            pStatement.setInt(1,this.orderID);
            ResultSet results = pStatement.executeQuery();

            results.next();

            assertEquals(amount,results.getInt("amount"));
            assertEquals(priority,results.getInt("priority"));
            assertEquals(status,results.getBoolean("status"));
            assertEquals(earliestDeliveryDate,new Date(results.getTimestamp("earliestdeliverydate").getTime()));
            assertEquals(latestDeliveryDate,new Date(results.getTimestamp("latestdeliverydate").getTime()));
            assertEquals(orderID,results.getInt("orderid"));
            assertEquals(productType,ProductTypeEnum.get(results.getString("producttype")));
        }   finally {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            for (Integer ID : this.orderIDsToBeRemoved) {
                pStatement.setInt(1,ID);
                pStatement.execute();
            }
            connection.close();
        }

    }

    @And("^the production order information is invalid$")
    public void theProductionOrderInformationIsInvalid() throws Throwable {
        amount = -1;
        productType = ProductTypeEnum.ALE;
        earliestDeliveryDate = new Date(50000000);
        latestDeliveryDate = new Date(0);
        priority=-1;
        orderID=1;
        status=true;
    }
}
