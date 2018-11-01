package physicalSimulation;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;
import physicalSimulation.SubscribePhysical;

public class StopReasonSubscriberPhysical implements IDataChangeCatcher {

    private SubscribePhysical test;

    public StopReasonSubscriberPhysical(SubscribePhysical test) {
        this.test = test;
    }

    @Override
    public void report(DataValue data) {
        test.setChangedStopReason(data.getValue().intValue());
    }

}
