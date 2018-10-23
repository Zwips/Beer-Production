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
public class MachineSpeed {
    
    private String identifier = "MachSpeed";

    float writeMachineSpeed(UaClient client, String prefix, Float value) throws ServiceException, StatusException {
        /*switch(PID){
            case 1: PID = 0;
            break;
            case 2: PID = 1;
            break;
            case 3: PID = 2;
            break;
            case 4: PID = 3;
            break;
            case 5: PID = 4;
        }*/
        NodeId node = new NodeId(6, prefix + this.identifier);
        DataValue dv = new DataValue(new Variant(value));


        client.writeValue(node, dv);

        return value;





    }


}
