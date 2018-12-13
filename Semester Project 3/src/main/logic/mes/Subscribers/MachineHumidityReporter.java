package logic.mes.Subscribers;

/** Represents a machine reporter for humidity
 * @author Asmus
 * @param report Method for logging humidity.
 */

import acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import logic.mes.MESOutFacade;
import logic.mes.Machine;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;

import java.util.Date;

public class MachineHumidityReporter implements IDataChangeCatcher {
    private Machine machine;
    private String factoryID;

    public MachineHumidityReporter(Machine machine, String factoryID)
    {
        this.machine = machine;
        this.factoryID = factoryID;
    }

    @Override
    public void report(DataValue newData) {
        Date date = new Date();

        try {
            MESOutFacade.getInstance().logHumidity(newData.getValue().floatValue(), date, (int)machine.readBatchIDCurrent(), factoryID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
