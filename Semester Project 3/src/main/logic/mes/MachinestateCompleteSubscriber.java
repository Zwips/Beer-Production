package logic.mes;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;

public class MachinestateCompleteSubscriber  implements IDataChangeCatcher  {

    private String machineID;
    private ProcessingPlant plant;

    MachinestateCompleteSubscriber(String machineID, ProcessingPlant plant) {
        this.machineID = machineID;
        this.plant = plant;
    }

    @Override
    public void report(DataValue newData) {
        if (newData.getValue().intValue() == 17) {
            plant.executeNextOrder(machineID);
        }
    }


}
