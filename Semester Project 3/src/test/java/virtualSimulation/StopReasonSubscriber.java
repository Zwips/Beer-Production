package virtualSimulation;

import acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class StopReasonSubscriber implements IDataChangeCatcher {

    private virtualSimulation.SubscribeSimulation test;

    public StopReasonSubscriber(virtualSimulation.SubscribeSimulation test) {
        this.test = test;
    }

    @Override
    public void report(DataValue newData) {
        test.setChangedStopReason(newData.getValue().intValue());
    }

}
