package machineConnection;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;

public interface ICommandNode {
    DataValue readNodeValue();
    NodeId getNodeId();
}
