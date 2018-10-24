package scada;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class MachineHumidityReporter implements IDataChangeCatcher {
    @Override
    public void report(DataValue data) {

    }
}
