package communication.SQLCommunication.selecters;

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
        this.conditionsBatch = "BatchID = ?";

        this.selectionsTemperature = "valuecelcius, timeofreading";
        this.tablesTemperature = "temperature";
        this.conditionsTemperature = "BatchID = ?";

        this.selectionsVibration = "valuepbs, timeofreading";
        this.tablesVibration = "vibration";
        this.conditionsVibration = "BatchID = ?";

        this.selectionsHumidity = "valuepercent, timeofreading";
        this.tablesHumidity= "humidity";
        this.conditionsHumidity = "BatchID = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public IBatch getBatch(int batchID){
        CommunicationBatch batch = new CommunicationBatch();;

        this.getGeneralBatchInfo(batch, batchID);

        this.getTemperatureInfo(batch, batchID);

        this.getVibrationInfo(batch, batchID);

        this.getHumidityInfo(batch, batchID);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batch;
    }

    private void getGeneralBatchInfo(CommunicationBatch batch, int batchID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

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

    private void getTemperatureInfo(CommunicationBatch batch, int batchID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

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

    private void getVibrationInfo(CommunicationBatch batch, int batchID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

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

    private void getHumidityInfo(CommunicationBatch batch, int batchID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

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