package communication.sqlcommunication.selecters;
/** Represents an machine Retriever
 * @author Michael P
 * @param getmachines retrieves the machines from the database
 */
import acquantiance.IMachineConnectionInformation;
import communication.sqlcommunication.dataclasses.CommunicationMachineConnectionInformation;
import communication.sqlcommunication.tools.*;

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

        try {
            if (results.isBeforeFirst()) {
                while (results.next()) {
                    machineConnectionInformation = new CommunicationMachineConnectionInformation();

                    machineConnectionInformation.setMachineID(results.getString("machineid"));
                    machineConnectionInformation.setMachineIP(results.getString("machine_ip"));
                    machineConnectionInformation.setMachineUsername(results.getString("machineuserid"));
                    machineConnectionInformation.setMachinePassword(results.getString("machinepassword"));

                    String factoryID = results.getString("factoryid");
                    if (map.containsKey(factoryID)){
                        map.get(factoryID).add(machineConnectionInformation);
                    } else {
                        machineConnectionInformations = new ArrayList<>();
                        machineConnectionInformations.add(machineConnectionInformation);
                        map.put(factoryID, machineConnectionInformations);
                    }
                }

            }

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
