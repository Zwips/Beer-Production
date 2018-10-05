/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package semester.project.pkg3;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UserIdentity;
import com.prosysopc.ua.client.AddressSpaceException;
import com.prosysopc.ua.client.ServerConnectionException;
import com.prosysopc.ua.client.UaClient;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import opcSamples.SampleConsoleClient;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.core.TimestampsToReturn;
import org.opcfoundation.ua.transport.security.SecurityMode;

/**
 *
 * @author HCHB
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private UaClient client;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SampleConsoleClient console = new SampleConsoleClient();
        this.setupUaClient();
        
      
        try {
            System.out.println(client.getAddressSpace().browseMethods(new NodeId(6, "NS")));
        } catch (ServiceException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StatusException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private void setupUaClient(){
        try {
            client = new UaClient("opc.tcp://127.0.0.1:4840");
            
            ApplicationDescription descr = new ApplicationDescription();
            descr.setApplicationName(new LocalizedText("super"+"@localost"));
            descr.setApplicationUri("urn:localhost:OPCUA:"+"super");
            descr.setProductUri("urn:localhost:OPCUA:"+"super");
            descr.setApplicationType(ApplicationType.Client);
             
            client.setSecurityMode(SecurityMode.NONE);
            
            try {
                client.setUserIdentity(new UserIdentity("sdu", "1234"));
            } catch (SessionActivationException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                client.connect();
            } catch (ServiceException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            try {
//                System.out.println(Arrays.toString(client.discoverEndpoints()));
//            } catch (ServiceException ex) {
//                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
