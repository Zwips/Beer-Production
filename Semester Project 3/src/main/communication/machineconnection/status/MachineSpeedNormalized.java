/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineconnection.status;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;

/**
 *
 * @author HCHB
 */
public class MachineSpeedNormalized {

    private String identifier = "MachSpeed";
    private NodeId node;

    public MachineSpeedNormalized(String prefix) {
        node = new NodeId(6, prefix+this.identifier);
    }

    public NodeId getNode() {
        return node;
    }

    public float readMachineSpeedNormalized(UaClient client) throws ServiceException, StatusException{

        DataValue data = client.readValue(node);
        float value = data.getValue().floatValue();

        return value;
    }
}
