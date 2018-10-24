package machineConnection;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;
import semester.project.pkg3.FXMLDocumentController;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        MachineConnection machineConnection = new MachineConnection();

        UaClient client = machineConnection.getConnection("127.0.0.1:4840", "1234","sdu");

        start(client);
    }

    private static void start(UaClient client) throws InterruptedException {

        reset(client);
//        sleep(1000);

        machSpeed(client);
//        sleep(1000);

        batchID(client);
//        sleep(1000);

        productID(client);
//        sleep(1000);

        amount(client);
//        sleep(1000);

        startMachine(client);
//        sleep(1000);
    }

    private static void amount(UaClient client) {
        String identifier = "Command.Parameter[2].Value";
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv1 = new DataValue(new Variant(1000F));

        try {
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv1));
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

    private static void productID(UaClient client) {
        String identifier = "Command.Parameter[1].Value";
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv1 = new DataValue(new Variant(3.0F));

        try {
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv1));
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

    private static void batchID(UaClient client) {
        String identifier = "Command.Parameter[0].Value";
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv1 = new DataValue(new Variant(new Float(50)));

        try {
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv1));
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

    private static void machSpeed(UaClient client) {
        String identifier = "Command.MachSpeed";
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv1 = new DataValue(new Variant(100F));

        try {
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv1));
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

    private static void startMachine(UaClient client) {
        String identifier = "Command.CntrlCmd";
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv1 = new DataValue(new Variant(2));

        try {
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv1));
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

    private static void reset(UaClient client) {
        String identifier = "Command.CntrlCmd";
        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
        DataValue dv1 = new DataValue(new Variant(1));

        try {
            System.out.println("sent command to: " + identifier);
            System.out.println(client.writeValue(node, dv1));
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

}
