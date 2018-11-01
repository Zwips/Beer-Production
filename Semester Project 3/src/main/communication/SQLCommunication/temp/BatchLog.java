package communication.SQLCommunication.temp;

import Acquantiance.IBatch;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.dataClasses.CommunicationBatch;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchLog {

    private String selections;
    private String tables;
    private String conditions;
    private DatabaseConnector connector;
    private Connection connection;

    public BatchLog() {
        // "SELECT Batch.BatchID, Batch.ProductType, Batch.Amount, Batch.Defective WHERE Batch.BatchID = ?";

        this.selections = "*";
        this.tables = "batch_log";
        this.conditions = "batchid = ?";

        this.connector = new DatabaseConnector();
        this.connection = connector.OpenConnection();
    }

    void getBatch(){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, 1));

        ResultSet results = new Select().query(connection, selections, tables, conditions,wildCardInfo);

        CommunicationBatch batch = null;
        try {
            //while (results.next()){
            results.next();
            System.out.println(results.getInt(1));
            System.out.println(results.getString(2));



            //}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
