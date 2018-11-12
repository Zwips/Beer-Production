package communication.SQLCommunication.inserters;

import Acquantiance.IMachineSpecification;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.Insert;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MachineInserter {
    private String values;
    private String tables;
    private Connection connection;


    public MachineInserter() {
        this.values = "(?,?,?,?,?)";
        this.tables = "machines(factoryid, machineid, machine_ip, machineuserid, machinepassword)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(String factoryID, String machineID, String machine_IP, String userID, String password) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, factoryID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.STRING, machine_IP));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.STRING, userID));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.STRING, password));


        if(new Insert().insertion(connection, tables, values, wildCardInfo)){
            System.out.println("it worked");
        }
        else{
            System.out.println("it failed");
        }
    }
}
