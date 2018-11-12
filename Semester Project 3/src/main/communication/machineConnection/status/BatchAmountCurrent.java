/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineConnection.status;

/** Represents the current amount in a Batch.
 * @author HCHB
 */

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;

public class BatchAmountCurrent {
        
    private String identifier = "Parameter[1].Value";
    private NodeId node;


    public BatchAmountCurrent(String prefix) {
        node = new NodeId(6, prefix+this.identifier);
    }

    public NodeId getNode() {
        return node;
    }

    public float readProductsInBatch(UaClient client) throws ServiceException, StatusException{

        DataValue data = client.readValue(node);
        float value = data.getValue().floatValue();
        
        return value;
    } 
}
