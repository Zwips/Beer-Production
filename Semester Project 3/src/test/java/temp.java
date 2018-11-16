import communication.SQLCommunication.inserters.MachineInserter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import logic.erp.ERPOutFacade;
import systemTest.ERPLevelInitializer;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class temp {
//
//    @Given("^The simulation is started again$")
//    public void theSimulationIsStarted() {
//
//        File file = new File("Simulation/start.bat");
//        Process process;
//
//        try {String[] command = { "cmd.exe", "/C", "Start", file.getAbsolutePath() };
//            Runtime runtime = Runtime.getRuntime();
//            process  = runtime.exec(command);
//            process.waitFor();
//
//            process.destroy();
//            process.destroyForcibly();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @And("^a Factory in the ERP system with the name TestFactory and a machine called TestMachine in the database$")
//    public void aFactoryInTheERPSystemWithTheNameTestFactoryAndAMachineCalledTestMachineInTheDatabase() throws Throwable {
//        new MachineInserter().insert("TestFactory", "TestMachine","127.0.0.1:4840", "sdu","1234");
//        ERPLevelInitializer.glue();
//    }
//
//    @Then("^TestFactory and TestMachine will be loaded$")
//    public void testfactoryAndTestMachineWillBeLoaded() throws Throwable {
//        try {
//            assertEquals(true, erp.checkForProcessingPlant("TestFactory"));
//            assertEquals(true, erp.checkForMachine("TestMachine"));
//        }
//        finally
//        {
//            ERPOutFacade.getInstance().deleteMachine("TestMachine");
//            erp = null;
//        }
//    }
//
//    @Given("^A batchID with int max is in batch_log in the database$")
//    public void aBatchIDWithIntMaxIsInBatch_logInTheDatabase() throws Throwable {
//        sql.InsertIntoBatch_log(Integer.MAX_VALUE,"TestMachine", -1);
//    }
//
//    @Then("^nextBatchID will be int max$")
//    public void nextbatchidWillBeIntMax() throws Throwable {
//        sleep(10000);   //TODO er der en grund til denne sleep??
//        try{
//            assertEquals(Integer.MAX_VALUE, erp.getNextBatchID());
//        } finally {
//            sql.deleteBatchLogByBatchID(Integer.MAX_VALUE);
//            erp = null;
//        }
//    }



//    Scenario: ERP will initialize and load factories and machines
//    Given The simulation is started again
//    And a Factory in the ERP system with the name TestFactory and a machine called TestMachine in the database
//    When an ERP system initialises
//    Then TestFactory and TestMachine will be loaded
//
//    Scenario: ERP will initialize and update nextBatchID
//    Given A batchID with int max is in batch_log in the database
//    When an ERP system initialises
//    Then nextBatchID will be int max


}
