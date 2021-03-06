package systemTest.SQLCommunication;

import acquantiance.IBusinessOrder;
import acquantiance.ProductTypeEnum;
import communication.ISQLCommunicationFacade;
import communication.sqlcommunication.SQLCommunicationFacade;
import communication.sqlcommunication.tools.DatabaseConnector;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.BusinessOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SQLOrders {

    private ISQLCommunicationFacade sqlModule;
//    private Connection connection;
    private int orderID;
    private int amount;
    private ProductTypeEnum productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;
    private IBusinessOrder order;
    private List<IBusinessOrder> pendingOrders;
    private List<IBusinessOrder> completedOrders;

    @Given("^a connection to the SQL module, orders$")
    public void aConnectionToTheSQLModuleOrders() throws Throwable {
        this.sqlModule = new SQLCommunicationFacade();
    }

    @Given("^a connection to the database,orders$")
    public void aConnectionToTheDatabaseOrders() throws Throwable {
        //this.connection = new DatabaseConnector().openConnection();
    }

    @Given("^that an order with ID -(\\d+) exists$")
    public void thatAnOrderWithIDExists(int orderID) throws Throwable {
        this.orderID = -orderID;
        this.amount = 1;
        this.productType = ProductTypeEnum.ALE;
        this.earliestDeliveryDate = new Date(1000000);
        this.latestDeliveryDate = new Date(5000000);
        this.priority = 1;

        try(Connection connection = new DatabaseConnector().openConnection()){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1,this.orderID);
            pStatement.execute();

            BusinessOrder order = new BusinessOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
            order.setOrderID(this.orderID);
            this.sqlModule.logOrder(order);
        }
    }

    @When("^retrieving an order with ID -(\\d+)$")
    public void retrievingAnOrderWithID(int orderID) throws Throwable {
        orderID = -orderID;
        order = this.sqlModule.selectFromOrder(orderID);
    }

    @Then("^the correct order is retrieved$")
    public void theCorrectOrderIsRetrieved() throws Throwable {
        try {
            assertEquals(this.amount, this.order.getAmount());
            assertEquals(this.productType,this.order.getProductType());
            assertEquals(this.earliestDeliveryDate,this.order.getEarliestDeliveryDate());
            assertEquals(this.latestDeliveryDate,this.order.getLatestDeliveryDate());
            assertEquals(this.priority,this.order.getPriority());
            assertEquals(this.orderID,this.order.getOrderID());
        } finally {
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1,this.orderID);
            pStatement.execute();
            connection.close();
        }
    }

    @SuppressWarnings("Duplicates")
    @Given("^that a pending order with ID -(\\d+) exists$")
    public void thatAPendingOrderWithIDExists(int orderID) throws Throwable {
        this.orderID = -orderID;
        this.amount = 1;
        this.productType = ProductTypeEnum.ALE;
        this.earliestDeliveryDate = new Date(1000000);
        this.latestDeliveryDate = new Date(5000000);
        this.priority = 1;

        try(Connection connection = new DatabaseConnector().openConnection()) {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1, this.orderID);
            pStatement.execute();

            BusinessOrder order = new BusinessOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
            order.setOrderID(this.orderID);
            order.setStatus(false);
            this.sqlModule.logOrder(order);
        }
    }

    @When("^retrieving a pending orders$")
    public void retrievingAPendingOrders() throws Throwable {
        pendingOrders = this.sqlModule.getPendingOrders(new Date(0), new Date(6000000));
    }

    @Then("^a pending order with ID -(\\d+) is retrieved$")
    public void aPendingOrderWithIDIsRetrieved(int orderID) throws Throwable {
        for (IBusinessOrder pendingOrder: this.pendingOrders) {
            if (pendingOrder.getOrderID() < 0){
                this.order = pendingOrder;
            }
        }

        try{
            assertEquals(this.amount, this.order.getAmount());
            assertEquals(this.productType,this.order.getProductType());
            assertEquals(this.earliestDeliveryDate,this.order.getEarliestDeliveryDate());
            assertEquals(this.latestDeliveryDate,this.order.getLatestDeliveryDate());
            assertEquals(this.priority,this.order.getPriority());
            assertEquals(this.orderID,this.order.getOrderID());
            assertEquals(false,this.order.getStatus());
        }  finally {
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1,this.orderID);
            pStatement.execute();
            connection.close();
        }
    }

    @Given("^that a completed order with ID -(\\d+) exists$")
    public void thatACompletedOrderWithIDExists(int orderID) throws Throwable {
        this.orderID = -orderID;
        this.amount = 1;
        this.productType = ProductTypeEnum.ALE;
        this.earliestDeliveryDate = new Date(1000000);
        this.latestDeliveryDate = new Date(5000000);
        this.priority = 1;

        try(Connection connection = new DatabaseConnector().openConnection()) {

            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1, this.orderID);
            pStatement.execute();

            BusinessOrder order = new BusinessOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
            order.setOrderID(this.orderID);
            this.sqlModule.logOrder(order);
            this.sqlModule.setOrderCompleted(this.orderID);
        }
    }

    @When("^retrieving completed orders$")
    public void retrievingCompletedOrders() throws Throwable {
        completedOrders = this.sqlModule.getCompletedOrders();
    }

    @SuppressWarnings("Duplicates")
    @Then("^the completed orders is retrieved$")
    public void theCompletedOrdersIsRetrieved() throws Throwable {
        for (IBusinessOrder completedOrder: this.completedOrders) {
            if (completedOrder.getOrderID() < 0){
                this.order = completedOrder;
            }
        }

        try{
            assertEquals(this.amount, this.order.getAmount());
            assertEquals(this.productType,this.order.getProductType());
            assertEquals(this.earliestDeliveryDate,this.order.getEarliestDeliveryDate());
            assertEquals(this.latestDeliveryDate,this.order.getLatestDeliveryDate());
            assertEquals(this.priority,this.order.getPriority());
            assertEquals(this.orderID,this.order.getOrderID());
            assertTrue(this.order.getStatus());
        }  finally {
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1,this.orderID);
            pStatement.execute();
            connection.close();
        }
    }

    @SuppressWarnings("Duplicates")
    @Given("^that an uncompleted order with ID -(\\d+) exists$")
    public void thatAnUncompletedOrderWithIDExists(int orderID) throws Throwable {
        this.orderID = -orderID;
        this.amount = 1;
        this.productType = ProductTypeEnum.ALE;
        this.earliestDeliveryDate = new Date(1000000);
        this.latestDeliveryDate = new Date(5000000);
        this.priority = 1;

        try(Connection connection = new DatabaseConnector().openConnection()) {

            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1, this.orderID);
            pStatement.execute();

            BusinessOrder order = new BusinessOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
            order.setOrderID(this.orderID);
            order.setStatus(false);
            this.sqlModule.logOrder(order);
        }
    }

    @When("^setting the order with ID -(\\d+) to completed$")
    public void settingTheOrderWithIDToCompleted(int orderID) throws Throwable {
        this.sqlModule.setOrderCompleted(-orderID);
    }

    @SuppressWarnings("Duplicates")
    @Then("^the order with ID -(\\d+) is set to completed$")
    public void theOrderWithIDIsSetToCompleted(int orderID) throws Throwable {
        this.order = this.sqlModule.selectFromOrder(-orderID);

        try{
            assertEquals(this.amount, this.order.getAmount());
            assertEquals(this.productType,this.order.getProductType());
            assertEquals(this.earliestDeliveryDate,this.order.getEarliestDeliveryDate());
            assertEquals(this.latestDeliveryDate,this.order.getLatestDeliveryDate());
            assertEquals(this.priority,this.order.getPriority());
            assertEquals(this.orderID,this.order.getOrderID());
            assertTrue(this.order.getStatus());
        }  finally {
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1,this.orderID);
            pStatement.execute();
        }
    }
}