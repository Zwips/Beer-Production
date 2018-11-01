package communication.SQLCommunication.temp;

import Acquantiance.IBatch;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.temp.dataClasses.CommunicationBatch;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchRetriever {

    private String selections;
    private String tables;
    private String conditions;

    public BatchRetriever() {
        // "SELECT Batch.BatchID, Batch.ProductType, Batch.Amount, Batch.Defective WHERE Batch.BatchID = ?";

        this.selections = "Batch.BatchID, Batch.ProductType, Batch.Amount, Batch.Defective";
        this.tables = "Batch";
        this.conditions = "Batch.BatchID = ?";
    }

    IBatch getBatch(int batchID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

        ResultSet results = new Select().query(selections, tables, conditions,wildCardInfo);

        CommunicationBatch batch = null;
        try {
            //while (results.next()){
            results.next();
            batch = new CommunicationBatch();

            batch.setBatchID(results.getInt("BatchID"));
            batch.setProduced(results.getInt("Amount"));
            batch.setDiscarded(results.getInt("Defective"));

            ProductTypeEnum type = ProductTypeEnum.get(results.getString("ProductType"));
            batch.setProductType(type);
            //}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batch;
    }
}
