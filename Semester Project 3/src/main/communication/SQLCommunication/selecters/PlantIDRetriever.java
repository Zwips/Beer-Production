package communication.SQLCommunication.selecters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlantIDRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public PlantIDRetriever() {
        this.selections = "factoryid";
        this.tables = "machines";
        this.conditions = "true = true";

        this.connection = new DatabaseConnector().openConnection();
    }

    public Set<String> getPlantIDs() {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        Set<String> plantIDs = new HashSet<>();
        String plantID;

        try {
            while (results.next()) {
                plantID = results.getString(1);
                plantIDs.add(plantID);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantIDs;
    }
}
