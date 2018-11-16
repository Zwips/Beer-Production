package logic.mes;

/** Represents a machine reporter for vibration.
 * @author Asmus
 * @param report Method for logging vibration.
 */

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.util.Date;

public class MachineVibrationReporter implements IDataChangeCatcher {
    private Machine machine;
    private String factoryID;

    MachineVibrationReporter(Machine machine, String factoryID)
    {
        this.machine = machine;
        this.factoryID = factoryID;
    }
    @Override
    public void report(DataValue data) {
        if(data.getValue().floatValue() < -2 ||data.getValue().floatValue() >2)
        {
            Date date = new Date(data.getServerTimestamp().getValue());

            try {
                MESOutFacade.getInstance().logVibration(data.getValue().floatValue(), date, (int)machine.readBatchIDCurrent(), factoryID);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

    }
}
