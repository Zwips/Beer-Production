/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineconnection.command;

/** Represents the machineSpeed commands.
 * @author HCHB
 *
 */

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;


public class MachineSpeed {

    private String identifier = "MachSpeed";
    private NodeId node;

    public MachineSpeed(String prefix){
        node = new NodeId(6, prefix + this.identifier);
    }

    public NodeId getNode() {
        return node;
    }

    public float readMachineSpeed(UaClient client) throws ServiceException, StatusException {
        DataValue data = client.readValue(node);
        float value = data.getValue().floatValue();

        return value;
    }

    public void setMachineSpeed(UaClient client, float amount) throws ServiceException, StatusException {
        DataValue dataValue = new DataValue(new Variant(amount));

        SendCommand.write(node,dataValue,client);
    }


}
