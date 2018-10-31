package logic.mes;

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.util.Date;

public class MachineVibrationReporter implements IDataChangeCatcher {
    private Machine machine;

    MachineVibrationReporter(Machine machine)
    {
        this.machine = machine;
    }
    @Override
    public void report(DataValue data) {
        if(data.getValue().floatValue() < -2 ||data.getValue().floatValue() >2)
        {
            Date date = new Date(data.getServerTimestamp().getValue());

            try {
                MESOutFacade.getInstance().logVibration(data.getValue().floatValue(), date, (int)machine.readBatchIDCurrent());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

    }
}
