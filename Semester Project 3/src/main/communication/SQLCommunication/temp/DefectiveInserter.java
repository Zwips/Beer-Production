package communication.SQLCommunication.temp;

import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.Insert;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DefectiveInserter {


    private String values;
    private String tables;
    private Connection connection;


    public DefectiveInserter() {
        // "INSERT INTO defectives(machineid, numberofdefective, productsinbatch, machinespeed, product) VALUES (?,?,?,?,?)";

        this.values = "(?,?,?,?)";
        this.tables = "defectives(machineid, numberofdefective, productsinbatch, machinespeed, product)";
        connection = new DatabaseConnector().OpenConnection();

    }


    public void insert(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.INT, numberOfDefective));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.FLOAT, productsInBatch));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.FLOAT, machineSpeed));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.STRING, product.getType()));

       new Insert().insertion(connection, tables, values, wildCardInfo);

    }
}
