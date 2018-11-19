package logic.mes;

/** Represents a machine reporter for humidity
 * @author Asmus
 * @param report Method for logging humidity.
 */

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.util.Date;

public class MachineHumidityReporter implements IDataChangeCatcher {
    private Machine machine;
    private String factoryID;

    MachineHumidityReporter(Machine machine, String factoryID)
    {
        this.machine = machine;
        this.factoryID = factoryID;
    }

    @Override
    public void report(DataValue newData) {
        Date date = new Date(newData.getServerTimestamp().getValue());

        try {
            MESOutFacade.getInstance().logHumidity(newData.getValue().floatValue(), date, (int)machine.readBatchIDCurrent(), factoryID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
