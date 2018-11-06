package logic.mes;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class MachineStopReasonIdReporter implements IDataChangeCatcher {
    Machine machine;
    MachineStopReasonIdReporter(Machine machine)
    {
        this.machine = machine;
    }
    @Override
    public void report(DataValue data) {

    }
}
