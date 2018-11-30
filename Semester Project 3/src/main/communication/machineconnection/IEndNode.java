package communication.machineconnection;

import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;

public interface IEndNode {

    DataValue readNodeValue();
    NodeId getNodeId();
}
