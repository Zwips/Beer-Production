package communication.machineConnection;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.UserIdentity;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.transport.security.SecurityMode;
import semester.project.pkg3.FXMLDocumentController;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

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
}
