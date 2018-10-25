package communication.machineConnection;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;

public interface IEndNode {

    DataValue readNodeValue();
    NodeId getNodeId();
    void writeCommand(Variant value);
}
