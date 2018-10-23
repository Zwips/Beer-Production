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

/**
 *
 * @author HCHB
 */
public class Control {
    
    private String identifier = "CntrlCmd";

    int readPackMLCommand(UaClient client, String prefix) throws ServiceException, StatusException {
        NodeId node = new NodeId(6, prefix+this.identifier);

        DataValue data = client.readValue(node);
        int value = data.getValue().intValue();

        return value;
    }

    boolean writePackMLCommand(){

        throw new UnsupportedOperationException();
    }
}
