package systemTest.ERPTest;

import acquantiance.IERPFacade;
import acquantiance.IMachineConnectionInformation;
import acquantiance.ProductTypeEnum;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.SQLCommunicationFacade;
import communication.SQLCommunication.tools.DatabaseConnector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERPOutFacade;
import logic.erp.ProductionOrder;
import logic.mes.Machine;
import systemTest.ERPLevelInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ERPInitialize {
    private ISQLCommunicationFacade sql = new SQLCommunicationFacade();

    private IERPFacade erp;
    private int maxOrderID;
    private int orderID;

    @Given("^A orderID with one below int max is in batch_log in  the database$")
    public void aOrderIDWithOneBelowIntMaxIsInBatch_logInTheDatabase()  throws Throwable {
        maxOrderID = Integer.MAX_VALUE-1;
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
        try{
            erp = ERPLevelInitializer.glue();
        } catch (Exception e){
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1, this.orderID);
            pStatement.execute();

            connection.close();
            throw e;
        }
    }

    @Then("^nextOrderID will be int max$")
    public void nextorderidWillBeIntMax() throws Throwable {
        try{
            assertEquals(Integer.MAX_VALUE, erp.getNextOrderID());
        } finally {
            sql.deleteOrderByOrderID(Integer.MAX_VALUE);
            sql.deleteOrderByOrderID(Integer.MAX_VALUE-1);
            erp = null;
        }
    }

    @Given("^that pending orders exist in the database$")
    public void thatPendingOrdersExistInTheDatabase() throws Throwable {
        try(Connection connection = new DatabaseConnector().openConnection()) {

            orderID = 500000000;
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, orderID) VALUES (?,?,?,?,?,?,?)");
            pStatement.setInt(1,100);
            pStatement.setString(2,ProductTypeEnum.PILSNER.getType());
            pStatement.setTimestamp(3,new Timestamp(0));
            pStatement.setTimestamp(4, new Timestamp(1000000000));
            pStatement.setInt(5, 1);
            pStatement.setBoolean(6, false);
            pStatement.setInt(7, orderID);

            pStatement.execute();
        }
    }

    @And("^that a machine is connnected$")
    public void thatAMachineIsConnnected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //TODO considering removing all of this type of conditions, and starting the simulation manually
        //throw new PendingException();
    }

    @Then("^at least one machine starts executing orders$")
    public void atLeastOneMachineStartsExecutingOrders() throws Throwable {
        try{

            Set<Machine> machines = new HashSet<>();

            for (List<IMachineConnectionInformation> infos : ERPOutFacade.getInstance().getMachines().values()) {
                for (IMachineConnectionInformation info : infos) {
                    machines.add(new Machine(info.getMachineID(), info.getMachineIP(), info.getMachineUsername(), info.getMachinePassword(),"derp"));
                }
            }

            boolean started = false;
            int i = 1;
            while (!started || i<10){
                for (Machine machine : machines) {
                    if (machine.readCurrentState() == 6){
                        started = true;
                    }
                }
                i++;
            }

            assertTrue(started);
        } finally {
            Connection connection = new DatabaseConnector().openConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?;");
            pStatement.setInt(1, this.orderID);
            pStatement.execute();

            connection.close();
        }

    }
}
