/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineConnection.admin;

/** Represents a stopReasonID.
 * @author HCHB
 *
 */

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;


public class StopReasonID {

    private String identifier = "StopReason.ID";
    private NodeId node;

    public StopReasonID(String prefix) {
        this.node = new NodeId(6, prefix+this.identifier);
    }

    public int readStopReasonID(UaClient client) throws ServiceException, StatusException {

        DataValue data = client.readValue(node);
        int value = data.getValue().intValue();

        return value;
    }

    public NodeId getNode(){
        return this.node;
    }

}
