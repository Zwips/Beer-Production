package communication.machineConnection;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;

public interface ICommandNode {
    DataValue readNodeValue();
    NodeId getNodeId();
    void writeCommand(Number value);
}
