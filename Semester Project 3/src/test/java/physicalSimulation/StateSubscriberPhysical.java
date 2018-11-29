package physicalSimulation;

import acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class StateSubscriberPhysical implements IDataChangeCatcher {

    private SubscribePhysical test;

    public StateSubscriberPhysical(SubscribePhysical test) {
        this.test = test;
    }

    @Override
    public void report(DataValue newData) {
        test.setChangedState(newData.getValue().intValue());
    }

}
