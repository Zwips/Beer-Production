package logic.mes;

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.util.Date;

public class MachineHumidityReporter implements IDataChangeCatcher {
    private Machine machine;

    MachineHumidityReporter(Machine machine)
    {
        this.machine = machine;
    }

    @Override
    public void report(DataValue data) {
        Date date = new Date(data.getServerTimestamp().getValue());

        try {
            MESOutFacade.getInstance().logHumidity(data.getValue().floatValue(), date, (int)machine.readBatchIDCurrent());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
