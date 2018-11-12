package communication.SQLCommunication.selecters;

import Acquantiance.IMachineConnectionInformation;
import Acquantiance.IMachineSpecification;
import communication.SQLCommunication.dataClasses.CommunicationMachineConnectionInformation;
import communication.SQLCommunication.tools.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MachinesRetriever {
    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;


    public MachinesRetriever() {
        this.selections = "";
        this.tables = "factories(machineid, machine_ip, userid, password, machine_specs, factoryid)";
        this.conditions = "true = ture";
        connection = new DatabaseConnector().openConnection();
    }

    public HashMap<String, List<IMachineConnectionInformation>> getMachines() {

        HashMap<String, List<IMachineConnectionInformation>> map = new HashMap<>();
        List<PrepareInfo> wildCardInfo = new ArrayList<>();

        List<IMachineConnectionInformation> machineConnectionInformations = new ArrayList<>();
        CommunicationMachineConnectionInformation machineConnectionInformation;

        ResultSet results =  new Select().query(connection, selections, tables, conditions, wildCardInfo);
        String factoryID = "";
        try {
        while (results.next()) {
            machineConnectionInformation = new CommunicationMachineConnectionInformation();

                machineConnectionInformation.setMachineID(results.getString(1));
                machineConnectionInformation.setMachineIP(results.getString(2));
                machineConnectionInformation.setMachineUsername(results.getString(3));
                machineConnectionInformation.setMachinePassword(results.getString(4));
//                for when machine specification is something we can use;
//                byte[] bytes = results.getBytes(5);
//                ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);
//                ObjectInputStream objectIS = new ObjectInputStream(byteArrayIS);
//                machineConnectionInformation.setMachineSpecification((IMachineSpecification) objectIS.readObject());

                if(results.getString(6).equals(factoryID)) {
                    machineConnectionInformations.add(machineConnectionInformation);
                    factoryID = results.getString(6);
                }
                else
                {
                    map.put(factoryID, machineConnectionInformations);
                    machineConnectionInformations = new ArrayList<>();
                    machineConnectionInformations.add(machineConnectionInformation);
                    factoryID = results.getString(6);
                }


    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
