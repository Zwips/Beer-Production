package logic.mes;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class MachineCurrentMachineStateReporter implements IDataChangeCatcher {
    private Machine machine;

    MachineCurrentMachineStateReporter(Machine machine)
    {
        this.machine = machine;
    }
    @Override
    public void report(DataValue data) {


        if(data.getValue().intValue() == 17)
        {
            machine.uploadBatchInfo();
        }
    }
}
