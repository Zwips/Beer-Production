package logic.mes;

import acquantiance.IDataChangeCatcher;
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

        switch (newData.getValue().intValue()) {
            case 17:
                plant.uploadBatchData(machineID);

                boolean toBeRemoved = plant.isRemoved(machineID);
                boolean removed = false;

                if (toBeRemoved) {
                    removed = plant.removeMachine(machineID);
                }

                if (!removed) {
                    plant.analyseProduction(machineID);

                    plant.executeNextOrder(machineID);
                }
                break;
        }

    }

}
