package logic.mes;

/** Represents a machine reporter for state change
 * @author Asmus
 * @param report Method for uploading Batch info if state reaches complete.
 *
 */

import acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.util.Date;

public class MachineOEE implements IDataChangeCatcher {
    private Machine machine;
    private String factoryID;

    MachineOEE(Machine machine, String factoryID)
    {
        this.machine = machine;
        this.factoryID = factoryID;
    }

    @Override
    public void report(DataValue newData) {

        String stateName = getStateName(newData);
        Date timeOfChange = new Date();
        boolean isProducing;
        if(newData.getValue().intValue() == 6)
        {
            isProducing = true;
        }
        else
        {
            isProducing = false;
        }

        try {
            MESOutFacade.getInstance().logOEE(factoryID,machine.getMachineID(),(int)machine.readBatchIDCurrent(),stateName,timeOfChange,isProducing);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private String getStateName(DataValue value){
        switch (value.getValue().intValue()){
            case 0:
                return "Deactivated";
            case 1:
                return "Clearing";
            case 2:
                return "Stopped";
            case 3:
                return "Starting";
            case 4:
                return "Idle";
            case 5:
                return "Suspended";
            case 6:
                return "Execute";
            case 7:
                return "Stopping";
            case 8:
                return "Aborting";
            case 9:
                return "Aborted";
            case 10:
                return "Holding";
            case 11:
                return "Held";


            case 15:
                return "Resetting";
            case 16:
                return "Completing";
            case 17:
                return "Complete";
            case 18:
                return "Deactivating";
            case 19:
                return "Activating";
            default:
                return "Error: machine state out of bounds";

        }



    }



}
