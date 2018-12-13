package logic.mes;

/** Represents a machine reporter for temperature.
 * @author Asmus
 * @param report Method for logging temperature.
 */

import acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;

import java.util.Date;

public class MachineTemperatureReporter implements IDataChangeCatcher {

    private Machine machine;
    private String factoryID;

    MachineTemperatureReporter(Machine machine, String factoryID) {
        this.machine = machine;
        this.factoryID = factoryID;
    }

    @Override
    public void report(DataValue newData) {
        Date date = new Date();

        try {
            MESOutFacade.getInstance().logTemperature(newData.getValue().floatValue(), date, (int)machine.readBatchIDCurrent(), factoryID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
