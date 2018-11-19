package virtualSimulation;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class StateSubscriber implements IDataChangeCatcher {

    private virtualSimulation.SubscribeSimulation test;

    public StateSubscriber(virtualSimulation.SubscribeSimulation test) {
        this.test = test;
    }

    @Override
    public void report(DataValue newData) {
        test.setChangedState(newData.getValue().intValue());
    }

}
