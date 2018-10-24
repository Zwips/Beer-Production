package machineConnection;

import machineConnection.command.*;

public interface ICommand {
    public Amount getAmount(String prefix);
    public BatchID getBatchId(String prefix);
    public Control geControl(String prefix);
    public MachineConnection gMachineConnection(String prefix);
    public MachineSpeed gMachineSpeed(String prefix);
    public ProductID getProductId(String prefix);

}
