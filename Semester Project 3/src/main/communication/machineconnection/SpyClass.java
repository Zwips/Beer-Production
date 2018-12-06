package communication.machineconnection;

import com.prosysopc.ua.ServiceException;
import communication.ISQLCommunicationFacade;
import communication.sqlcommunication.SQLCommunicationFacade;
import logic.mes.MachineSpecifications;

public class SpyClass {

    private static MachineConnection connection = new MachineConnection("10.112.254.165", "sdu","1234");
    static MachineSpecifications specs = new MachineSpecifications();

    public static void main(String[] args) {

    }

    private SpyClass(){
        try {
            connection.subscribeToCurrentState(new SpySubscription(this, "testFactory"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public int getBatchID(){
        try {
            return (int)connection.readBatchIDCurrent();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return -1;
    }
    void logDefectives(){
        ISQLCommunicationFacade sql = new SQLCommunicationFacade();
        try {
            sql.logDefectives("speedTest", connection.readNumberOfDefectiveProducts(),
                    connection.readProductsInBatch(), connection.readMachineSpeedCurrent(),
                    specs.getProductType(connection.readCurrentProductID()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


}
