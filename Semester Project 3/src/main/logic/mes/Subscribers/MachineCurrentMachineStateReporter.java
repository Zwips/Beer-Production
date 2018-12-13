package logic.mes.Subscribers;

/** Represents a machine reporter for state change
 * @author Asmus
 * @param report Method for uploading Batch info if state reaches complete.
 *
 */

import acquantiance.IDataChangeCatcher;
import logic.mes.Machine;
import org.opcfoundation.ua.builtintypes.DataValue;

public class MachineCurrentMachineStateReporter implements IDataChangeCatcher {
    private Machine machine;

    public MachineCurrentMachineStateReporter(Machine machine)
    {
        this.machine = machine;
    }

    @Override
    public void report(DataValue newData) {


        if(newData.getValue().intValue() == 17)
        {
            //machine.uploadBatchInfo();
            //machine.reportCompletion();
        }
    }
}
