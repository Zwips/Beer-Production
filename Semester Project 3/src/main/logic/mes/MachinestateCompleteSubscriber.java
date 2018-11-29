package logic.mes;

import Acquantiance.IDataChangeCatcher;
import communication.SQLCommunication.selecters.BatchLogByBatchIDRetriever;
import org.opcfoundation.ua.builtintypes.DataValue;

import static java.lang.Thread.sleep;

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

                plant.executeNextOrder(machineID);
                break;
        }





    }


}
