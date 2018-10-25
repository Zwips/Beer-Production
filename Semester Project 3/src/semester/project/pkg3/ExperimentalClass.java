/*
package semester.project.pkg3;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UserIdentity;
import com.prosysopc.ua.client.UaClient;
import opcSamples.SampleConsoleClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.transport.security.SecurityMode;

import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class ExperimentalClass {

    private UaClient client;

    private void handleButtonAction() {
        Label label = new Label();

        try {
            NodeId node = new NodeId(6, "::Program:Cube.Status.StateCurrent");
            NodeId node2 = new NodeId(6, "::Program:Cube.Status.CurMatchSpeed");
            NodeId node5 = new NodeId(6, "::Program:Cube.Status.Parameter[1].Value");
            NodeId node3 = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");
            NodeId node4 = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
            System.out.println("You clicked me!");
            label.setText("Start");
            writeValue("Command.CntrlCmd", 3);
            sleep(2000);
            writeValue("Command.CntrlCmd", 5);
            sleep(2000);
            writeValue("Command.CntrlCmd", 1);
            sleep(2000);
            writeValue("Command.CntrlCmd", 2);
            sleep(1000);
            System.out.println("current type");
//            System.out.println(client.readValue(node4));
//            writeValue("Command.MachSpeed", 50);
            sleep(2000);
            System.out.println(client.readValue(node));
            System.out.println(client.readValue(node2));
            sleep(5000);
            System.out.println("");
            System.out.print("beers produced: ");
            System.out.println(client.readValue(node3));
            System.out.println(client.readValue(node5));
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StatusException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialize(URL url, ResourceBundle rb) {

        SampleConsoleClient console = new SampleConsoleClient();
        this.setupUaClient();

        try{
            System.out.println("indsætter amount");
//            System.out.println(client.readValue(new NodeId(6, "::Program:Cube.Command.Parameter[2]")));
            float i = 100;
            writeValue("Command.Parameter[2].Value", i);
            sleep(1000);
            System.out.println("insætter type");
            writeValue("Command.Parameter[1].Value", 1f);
            sleep(1000);
            System.out.println("indsætter id");
            writeValue("Command.Parameter[0].Value", 12014f);

        }catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            System.out.println(client.getAddressSpace().browseMethods(new NodeId(6, "NS")));
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }



//        try {
//            System.out.println(client.getAddressSpace().browseMethods(new NodeId(6, "NS")));
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void writeValue(String identifier, Object value) {
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv = new DataValue(new Variant(value));
        DataValue dv1 = new DataValue(new Variant(24.0F));


        try {
            System.out.println("**********************************");
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv));
            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
            System.out.println(client.readValue(node));
            System.out.println("**********************************");
            System.out.println("");
        } catch (ServiceException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StatusException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public Object readValue(String identifier) {
//        try {
//            DataValue value = client.readValue(0, TimestampsToReturn.Both, new NodeId(6, "::Program:Cube."+identifier));
//            return value.getValue().getValue();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return null;
////    }
//    }


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
*/