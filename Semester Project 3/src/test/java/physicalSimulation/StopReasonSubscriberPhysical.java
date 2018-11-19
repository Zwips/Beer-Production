package physicalSimulation;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class StopReasonSubscriberPhysical implements IDataChangeCatcher {

    private SubscribePhysical test;

    public StopReasonSubscriberPhysical(SubscribePhysical test) {
        this.test = test;
    }

    @Override
    public void report(DataValue newData) {
        test.setChangedStopReason(newData.getValue().intValue());
    }

}
