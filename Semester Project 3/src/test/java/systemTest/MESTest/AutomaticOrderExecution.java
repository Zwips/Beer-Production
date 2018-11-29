package systemTest.MESTest;

import acquantiance.IERPFacade;
import acquantiance.IMachineConnectionInformation;
import acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import logic.mes.MESOutFacade;
import systemTest.ERPLevelInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutomaticOrderExecution {

    private IERPFacade erp;

    @Given("^there is an ERP system, execute orders$")
    public void thereIsAnERPSystemExecuteOrders() throws Throwable {
        erp = ERPLevelInitializer.glue();
    }

    @Given("^an order exists in the factory$")
    public void anOrderExistsInTheFactory() throws Throwable {
        this.clearOrders();

        erp.addOrder(20, ProductTypeEnum.PILSNER, new Date(0), new Date(), 1);
        erp.addOrder(20, ProductTypeEnum.PILSNER, new Date(0), new Date(), 1);
        erp.addOrder(20, ProductTypeEnum.PILSNER, new Date(0), new Date(), 1);
        erp.addOrder(20, ProductTypeEnum.PILSNER, new Date(0), new Date(), 1);
        erp.addOrder(20, ProductTypeEnum.PILSNER, new Date(0), new Date(), 1);
    }

    @When("^the machine completes an order$")
    public void theMachineCompletesAnOrder() throws Throwable {

        HashMap<String, List<IMachineConnectionInformation>> machines = MESOutFacade.getInstance().getMachines();

        boolean completed = false;
        //MachineConnection machineconnection = new MachineConnection(IPAddress, userID, password);

//        for (int i = 0; i < ; i++) {
//            if (MESOutFacade.getInstance().){
//                completed = true;
//                break;
//            }
//            sleep(100);
//        }



        assertTrue(completed);
    }












    private void clearOrders(){
        Connection connection = new DatabaseConnector().openConnection();
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement("DELETE FROM orders");
            pStatement.execute();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }


    }
}
