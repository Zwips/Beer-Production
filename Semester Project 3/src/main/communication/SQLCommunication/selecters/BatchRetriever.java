package communication.SQLCommunication.selecters;
/** Represents an batch Retriever
 * @author Michael P
 * @param BatchRetriever method retrieves the batch from the database with given batchid containing batchid, producttype, amount, defective, vibration, temperature and humidity
 * @param getBatch creates the the object CommunicationBatch & returns it.
 * @param getGeneralBatchInfo retrieves the batch info batchid, amount & defective from communicationsbatch.
 * @param getTemperatureInfo retrieves the valuecelcius & timestamp from communicationsbatch
 * @param getVibrationInfo retrieves the valuePBS & timestamp from communicationsbatch
 * @param getHumidityInfo retrieves the valuepercent & timestamp from communicationsbatch
 *
 */
import Acquantiance.IBatch;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.dataClasses.CommunicationBatch;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BatchRetriever {

    //TODO is this supposed to correspond to the batchReport??

    private String selectionsVibration;
    private String tablesVibration;
    private String conditionsVibration;

    private String selectionsHumidity;
    private String tablesHumidity;
    private String conditionsHumidity;

    private String selectionsTemperature;
    private String tablesTemperature;
    private String conditionsTemperature;

    private String selectionsBatch;
    private String tablesBatch;
    private String conditionsBatch;

    private Connection connection;

    public BatchRetriever() {
        this.selectionsBatch = "BatchID, ProductType, Amount, Defective";
        this.tablesBatch = "Batch";
        this.conditionsBatch = "BatchID = ? AND factoryid = ?";

        this.selectionsTemperature = "valuecelcius, timeofreading";
        this.tablesTemperature = "temperature";
        this.conditionsTemperature = "BatchID = ? AND factoryid = ?";

        this.selectionsVibration = "valuepbs, timeofreading";
        this.tablesVibration = "vibration";
        this.conditionsVibration = "BatchID = ? AND factoryid = ?";

        this.selectionsHumidity = "valuepercent, timeofreading";
        this.tablesHumidity= "humidity";
        this.conditionsHumidity = "BatchID = ? AND factoryid = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public IBatch getBatch(int batchID, String factoryID){
        CommunicationBatch batch = new CommunicationBatch();;

        this.getGeneralBatchInfo(batch, batchID, factoryID);

        this.getTemperatureInfo(batch, batchID, factoryID);

        this.getVibrationInfo(batch, batchID, factoryID);

        this.getHumidityInfo(batch, batchID, factoryID);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new DatabaseConnector().closeConnection(connection);
        return batch;
    }

    private void getGeneralBatchInfo(CommunicationBatch batch, int batchID, String factoryID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selectionsBatch, tablesBatch, conditionsBatch, wildCardInfo);

        try {
            results.next();

            batch.setBatchID(results.getInt("BatchID"));
            batch.setProduced(results.getInt("Amount"));
            batch.setDiscarded(results.getInt("Defective"));

            ProductTypeEnum type = ProductTypeEnum.get(results.getString("ProductType"));
            batch.setProductType(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getTemperatureInfo(CommunicationBatch batch, int batchID, String factoryID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selectionsTemperature, tablesTemperature, conditionsTemperature, wildCardInfo);
        Map<Date, Float> temperatureMeasurements = new HashMap<>();

        try {
            while (results.next()){
                Date date = new Date(results.getTimestamp("timeofreading").getTime());
                Float temperature = results.getFloat("valuecelcius");
                temperatureMeasurements.put(date, temperature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        batch.setTemperatures(temperatureMeasurements);
    }

    private void getVibrationInfo(CommunicationBatch batch, int batchID, String factoryID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selectionsVibration, tablesVibration, conditionsVibration, wildCardInfo);
        Map<Date, Float> vibrationMeasurements = new HashMap<>();

        try {
            while (results.next()){
                Date date = new Date(results.getTimestamp("timeofreading").getTime());
                Float vibration = results.getFloat("valuepbs");
                vibrationMeasurements.put(date, vibration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        batch.setVibrations(vibrationMeasurements);
    }

    private void getHumidityInfo(CommunicationBatch batch, int batchID, String factoryID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selectionsHumidity, tablesHumidity, conditionsHumidity, wildCardInfo);

        Map<Date, Float> humidityMeasurements = new HashMap<>();

        try {
            while (results.next()){
                Date date = new Date(results.getTimestamp("timeofreading").getTime());
                Float humidity = results.getFloat("valuepercent");
                humidityMeasurements.put(date, humidity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        batch.setHumidity(humidityMeasurements);
    }
}
