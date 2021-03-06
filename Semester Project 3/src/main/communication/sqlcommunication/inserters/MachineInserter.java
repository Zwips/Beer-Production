package communication.sqlcommunication.inserters;
/** Represents an machine inserter
 * @author Michael P
 * @param insert inserts a new machine into the database
 */
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineInserter {

    private String values;
    private String tables;
    private Connection connection;

    public MachineInserter() {
        this.tables = "machines(factoryid, machineid, machine_ip, machineuserid, machinepassword)";
        this.values = "(?,?,?,?,?)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(String factoryID, String machineID, String machine_IP, String userID, String password) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, factoryID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.STRING, machine_IP));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.STRING, userID));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.STRING, password));

        new Insert().insertion(connection, tables, values, wildCardInfo);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
