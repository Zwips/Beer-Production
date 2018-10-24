/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineConnection.admin;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;

/**
 *
 * @author HCHB
 */
public class StopReasonValue {

    private String identifier = "StopReason.Value";
    private NodeId node;

    public StopReasonValue(String prefix) {
        this.node = new NodeId(6, prefix+this.identifier);
    }

    public int readStopReasonValue(UaClient client) throws ServiceException, StatusException {
        DataValue data = client.readValue(node);
        int value = data.getValue().intValue();

        return value;
    }
    
}
