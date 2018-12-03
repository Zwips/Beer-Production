package systemTest.MESTest;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import logic.erp.ProductionOrder;
import org.junit.Test;
import systemTest.ERPLevelInitializer;

import java.sql.*;


public class BatchReport {
    @Test
    void testBatchReport() {
        try(Connection connection = new DatabaseConnector().openConnection()){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM orders");
            pStatement.execute();
            pStatement = connection.prepareStatement("INSERT INTO public.orders(\n" +
                    "\tamount, priority, status, earliestdeliverydate, latestdeliverydate, producttype, orderid)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?);");
            pStatement.setInt(1, 20);
            pStatement.setInt(2, 1);
            pStatement.setBoolean(3, false);
            pStatement.setTimestamp(4, new Timestamp(1700000));
            pStatement.setTimestamp(5, new Timestamp(2700000));
            pStatement.setString(6, String.valueOf(ProductTypeEnum.PILSNER));
            pStatement.setInt(7,41);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        ERPLevelInitializer.glue();

    }
}
