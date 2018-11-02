package communication.machineConnection;

import communication.machineConnection.command.*;

public interface ICommand {
    Amount getAmount(String prefix);
    BatchID getBatchId(String prefix);
    Control getControl(String prefix);
    MachineSpeed getMachineSpeed(String prefix);
    ProductID getProductId(String prefix);

}
