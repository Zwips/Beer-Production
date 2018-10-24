package machineConnection;

import machineConnection.command.*;

public interface ICommand {
    public Amount getAmount();
    public BatchID getBatchId();
    public Control geControl();
    public MachineConnection gMachineConnection();
    public MachineSpeed gMachineSpeed();
    public ProductID getProductId();

}
