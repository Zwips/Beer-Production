/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineConnection.command;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;

/**
 *
 * @author HCHB
 */
public class Control {
    
    private String identifier = "CntrlCmd";
    private NodeId node;

    Control(String prefix){
        node = new NodeId(6, prefix + this.identifier);
    }
    public NodeId getNode() {
        return node;
    }
    int readControlCommand(UaClient client, String prefix) throws ServiceException, StatusException {
        DataValue data = client.readValue(node);
        int value = data.getValue().intValue();

        return value;
    }

    void setControlCommand(UaClient client, int amount) throws ServiceException, StatusException {
        DataValue dataValue = new DataValue(new Variant(amount));

        SendCommand.write(node,dataValue,client);
    }
}
