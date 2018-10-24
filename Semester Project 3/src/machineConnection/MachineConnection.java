/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package machineConnection;

import Acquantiance.IMachineConnection;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UserIdentity;
import com.prosysopc.ua.client.UaClient;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import machineConnection.admin.*;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.transport.security.SecurityMode;
import semester.project.pkg3.FXMLDocumentController;

/**
 *
 * @author HCHB
 */
public class MachineConnection implements IMachineConnection {
    
    private String identifier="::Program:Cube.";

    public UaClient getConnection(String address, String password, String userID){
        UaClient client;
        
        try {
            client = new UaClient("opc.tcp://"+address);
            
            ApplicationDescription descr = new ApplicationDescription();
            descr.setApplicationName(new LocalizedText("super"+"@localost"));
            descr.setApplicationUri("urn:localhost:OPCUA:"+"super");
            descr.setProductUri("urn:localhost:OPCUA:"+"super");
            descr.setApplicationType(ApplicationType.Client);
            
            client.setSecurityMode(SecurityMode.NONE);
            
            try {
                client.setUserIdentity(new UserIdentity(userID, password));
            } catch (SessionActivationException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                client.connect();
            } catch (ServiceException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return client;
        } catch (URISyntaxException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public float readCurrentProductID(UaClient client) throws ServiceException, StatusException {
        Admin admin = new Admin();

        CurrentProductType prod = admin.getCurrentProductType(identifier);
        return prod.readCurrentProductID(client);
    }

    @Override
    public int readNumberOfDefectiveProducts(UaClient client) throws ServiceException, StatusException {
        Admin admin = new Admin();

        DefectiveProducts prod = admin.getDefectiveProducts(identifier);
        return prod.readNumberOfDefectiveProducts(client);
    }

    @Override
    public int  readNumberOfProducedProducts(UaClient client) throws ServiceException, StatusException {
        Admin admin = new Admin();

        ProducedProducts prod = admin.getProducedProducts(identifier);
        return prod.readNumberOfProducedProducts(client);
    }

    @Override
    public int readStopReasonID(UaClient client) throws ServiceException, StatusException {
        Admin admin = new Admin();

        StopReasonID prod = admin.getStopReasonId(identifier);
        return prod.readStopReasonID(client);
    }

    @Override
    public int readStopReasonValue(UaClient client) throws ServiceException, StatusException {
        Admin admin = new Admin();

        StopReasonValue prod = admin.getStopReasonValue(identifier);
        return prod.readStopReasonValue(client);
    }

    @Override
    public float readVibration(UaClient client) throws ServiceException, StatusException {
        return 0;
    }


}
