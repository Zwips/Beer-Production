package physicalSimulation;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;
import physicalSimulation.SubscribePhysical;

public class StateSubscriberPhysical implements IDataChangeCatcher {

    private SubscribePhysical test;

    public StateSubscriberPhysical(SubscribePhysical test) {
        this.test = test;
    }

    @Override
    public void report(DataValue data) {
        test.setChangedState(data.getValue().intValue());
    }

}
