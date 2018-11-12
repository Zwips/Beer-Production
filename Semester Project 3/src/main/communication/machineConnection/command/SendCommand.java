package communication.machineConnection.command;

/** Represents a client that sends a command.
 * @author Asmus
 * @param write Method for sending a command.
 */

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;

public class SendCommand {

    private static NodeId changeNode = new NodeId(6, "::Program:Cube.Command.CmdChangeRequest");

    static void write(NodeId node, DataValue dataValue, UaClient client) throws ServiceException, StatusException {
        client.writeValue(node, dataValue);
        client.writeValue(changeNode, new DataValue(new Variant(true)));
    }
}
