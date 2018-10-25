package communication.machineConnection;

import communication.machineConnection.command.*;

public interface ICommand {
    public Amount getAmount(String prefix);
    public BatchID getBatchId(String prefix);
    public Control getControl(String prefix);
    public MachineSpeed getMachineSpeed(String prefix);
    public ProductID getProductId(String prefix);

}
