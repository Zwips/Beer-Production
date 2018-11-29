/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineconnection.command;

/** Represents control commands.
 * @author HCHB
 *
 */

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;


public class Control {
    
    private String identifier = "CntrlCmd";
    private NodeId node;

    public Control(String prefix){
        node = new NodeId(6, prefix + this.identifier);
    }

    public NodeId getNode() {
        return node;
    }

    public int readControlCommand(UaClient client) throws ServiceException, StatusException {
        DataValue data = client.readValue(node);
        int value = data.getValue().intValue();

        return value;
    }

    public void setControlCommand(UaClient client, int amount) throws ServiceException, StatusException {
        DataValue dataValue = new DataValue(new Variant(amount));

        SendCommand.write(node,dataValue,client);
    }
}
