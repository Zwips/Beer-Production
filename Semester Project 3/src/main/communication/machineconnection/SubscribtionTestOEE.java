package communication.machineconnection;

import acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import logic.mes.MESOutFacade;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.io.IOException;
import java.util.Date;

public class SubscribtionTestOEE implements IDataChangeCatcher {
    private Test test;
    private String factoryID;

    public SubscribtionTestOEE(Test test, String factoryID){
        this.test = test;
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

        MESOutFacade.getInstance().logOEE(factoryID,"speedTest",test.getBatchID(),stateName,timeOfChange,isProducing);
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

