package communication.sqlcommunication.inserters;
/** Represents an defective inserter
 * @author Michael P
 * @param DefectiveInserter constructor creates the object containing machineid, numberofdefectives, productsinbatch, machinespeed & producttype.
 * @param insert method inserts the object into the the defectives table in the database.
 */
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DefectiveInserter {


    private String values;
    private String tables;
    private Connection connection;


    public DefectiveInserter() {
        // "INSERT INTO defectives(machineid, numberofdefective, productsinbatch, machinespeed, product) VALUES (?,?,?,?,?)";

        this.values = "(?,?,?,?,?)";
        this.tables = "defectives(machineid, numberofdefective, productsinbatch, machinespeed, product)";
        connection = new DatabaseConnector().openConnection();
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
