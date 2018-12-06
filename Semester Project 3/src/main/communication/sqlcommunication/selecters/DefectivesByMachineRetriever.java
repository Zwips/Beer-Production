package communication.sqlcommunication.selecters;

import acquantiance.IErrorRateDataPoint;
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.dataclasses.ErrorRateDataPoint;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefectivesByMachineRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public DefectivesByMachineRetriever() {
        this.selections = "*";
        this.tables = "defectives";
        this.conditions = "product = ? AND machineid = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public List<IErrorRateDataPoint> getDefectives(String machineID, ProductTypeEnum type){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, type.getType()));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        List<IErrorRateDataPoint> defectiveList = new ArrayList<>();
        try {
            results.isBeforeFirst();

            while(results.next()){
                int defectives = results.getInt("numberofdefective");
                double batchSize = results.getDouble("productsinbatch");
                double speed = results.getDouble("machinespeed");
                ProductTypeEnum productType = ProductTypeEnum.get(results.getString("product"));
                defectiveList.add(new ErrorRateDataPoint(defectives, (int) batchSize, speed, productType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new DatabaseConnector().closeConnection(connection);

        return defectiveList;
    }
}