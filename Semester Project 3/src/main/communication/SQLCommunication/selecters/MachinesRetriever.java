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
        this.selections = "*";
        this.tables = "machines";
        this.conditions = "true = true";
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
            results.next();
            factoryID = results.getString("factoryid");

            do {
                machineConnectionInformation = new CommunicationMachineConnectionInformation();

                machineConnectionInformation.setMachineID(results.getString("machineid"));
                machineConnectionInformation.setMachineIP(results.getString("machine_ip"));
                machineConnectionInformation.setMachineUsername(results.getString("machineuserid"));
                machineConnectionInformation.setMachinePassword(results.getString("machinepassword"));
//                for when machine specification is something we can use;
//                byte[] bytes = results.getBytes(5);
//                ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);
//                ObjectInputStream objectIS = new ObjectInputStream(byteArrayIS);
//                machineConnectionInformation.setMachineSpecification((IMachineSpecification) objectIS.readObject());

                String newFactoryID = results.getString("factoryid");
                if (map.containsKey(newFactoryID)){
                    map.get(newFactoryID).add(machineConnectionInformation);
                } else {
                    machineConnectionInformations = new ArrayList<>();
                    machineConnectionInformations.add(machineConnectionInformation);
                    map.put(factoryID, machineConnectionInformations);
                }

                /*if(results.getString("factoryid").equals(factoryID)) {
                    machineConnectionInformations.add(machineConnectionInformation);
                } else {
                    map.put(factoryID, machineConnectionInformations);
                    machineConnectionInformations = new ArrayList<>();
                    machineConnectionInformations.add(machineConnectionInformation);
                    factoryID = results.getString("factoryid");
                }*/

            } while (results.next());

            map.put(factoryID, machineConnectionInformations);
        } catch (SQLException e) {
        }
        return map;
    }

    @SuppressWarnings("Duplicates")
    public List<IMachineConnectionInformation> getMachines(String plantID) {

        String conditions = "factoryid = ?";
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1,PrepareType.STRING, plantID));

        List<IMachineConnectionInformation> machineConnectionInformations = new ArrayList<>();
        CommunicationMachineConnectionInformation machineConnectionInformation;

        ResultSet results =  new Select().query(connection, selections, tables, conditions, wildCardInfo);

        try {
            while (results.next()) {
                machineConnectionInformation = new CommunicationMachineConnectionInformation();

                machineConnectionInformation.setMachineID(results.getString("machineid"));
                machineConnectionInformation.setMachineIP(results.getString("machine_ip"));
                machineConnectionInformation.setMachineUsername(results.getString("machineuserid"));
                machineConnectionInformation.setMachinePassword(results.getString("machinepassword"));

                machineConnectionInformations.add(machineConnectionInformation);
            }

        } catch (SQLException e) {

        }

        return machineConnectionInformations;
    }
}
