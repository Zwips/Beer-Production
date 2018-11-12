package logic.mes;

/** Represents a machine reporter for temperature.
 * @author Asmus
 * @param report Method for logging temperature.
 */

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.util.Date;

public class MachineTemperatureReporter implements IDataChangeCatcher {

    private Machine machine;

    MachineTemperatureReporter(Machine machine)
    {
        this.machine = machine;
    }
    @Override
    public void report(DataValue data) {
        Date date = new Date(data.getServerTimestamp().getValue());

        try {
            MESOutFacade.getInstance().logTemperature(data.getValue().floatValue(), date, (int)machine.readBatchIDCurrent());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
