package communication.sqlcommunication.inserters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchReportInserter {

    private String values;
    private String tables;
    private Connection connection;

    public BatchReportInserter() {
        this.tables = "batchreport(BatchID, FactoryID, BatchReport)";
        this.values = "(?, ?, ?)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(int batchID, String factoryID, File batchReport) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.BYTEARRAY, batchReport));

        new Insert().insertion(connection, tables, values, wildCardInfo);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
