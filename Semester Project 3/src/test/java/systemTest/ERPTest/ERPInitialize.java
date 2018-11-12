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

    @Given("^The simulation is started again$")
    public void theSimulationIsStarted() {

        File file = new File("Simulation/start.bat");
        Process process;

        try {String[] command = { "cmd.exe", "/C", "Start", file.getAbsolutePath() };
            Runtime runtime = Runtime.getRuntime();
            process  = runtime.exec(command);
            process.waitFor();

            process.destroy();
            process.destroyForcibly();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @And("^a Factory in the ERP system with the name TestFactory and a machine called TestMachine in the database$")
    public void aFactoryInTheERPSystemWithTheNameTestFactoryAndAMachineCalledTestMachineInTheDatabase() throws Throwable {
        new MachineInserter().insert("TestFactory", "TestMachine","127.0.0.1:4840", "sdu","1234");
        ERPLevelInitializer.glue();
    }



    @Then("^TestFactory and TestMachine will be loaded$")
    public void testfactoryAndTestMachineWillBeLoaded() throws Throwable {
        try {
            assertEquals(true, erp.checkForProcessingPlant("TestFactory"));
            assertEquals(true, erp.checkForMachine("TestMachine"));
        }
        finally
        {
            ERPOutFacade.getInstance().deleteMachine("TestMachine");
            erp = null;
        }
    }

    @Given("^A batchID with int max is in batch_log in the database$")
    public void aBatchIDWithIntMaxIsInBatch_logInTheDatabase() throws Throwable {
        sql.InsertIntoBatch_log(Integer.MAX_VALUE,"TestMachine", -1);
    }

    @When("^an ERP system initialises$")
    public void anERPSystemInitialises() throws Throwable {
        erp = new ERP();
    }

    @Then("^nextBatchID will be int max$")
    public void nextbatchidWillBeIntMax() throws Throwable {
        sleep(10000);   //TODO er der en grund til denne sleep??
        try{
            assertEquals(Integer.MAX_VALUE, erp.getNextBatchID());
        } finally {
            sql.deleteBatchLogByBatchID(Integer.MAX_VALUE);
            erp = null;
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
            erp = null;
        }
    }

}
