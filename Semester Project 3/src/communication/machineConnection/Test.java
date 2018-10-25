
package communication.machineConnection;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;

import java.io.*;

import static java.lang.Thread.sleep;

public class Test {
    static int machineSpeed = 575;
    static int numberOfRuns = 0;

//<editor-fold desc="Raw machine start">
//    public static void main(String[] args) throws InterruptedException {
//
//        MachineConnection machineConnection = new MachineConnection();
//
//        UaClient client = machineConnection.getConnection("127.0.0.1:4840", "1234","sdu");
//
//        start(client);
//    }
//
//    private static void start(UaClient client) throws InterruptedException {
//
//        reset(client);
////        sleep(1000);
//
//        machSpeed(client);
////        sleep(1000);
//
//        batchID(client);
////        sleep(1000);
//
//        productID(client);
////        sleep(1000);
//
//        amount(client);
////        sleep(1000);
//
//        startMachine(client);
////        sleep(1000);
//    }
//
//    private static void amount(UaClient client) {
//        String identifier = "Command.Parameter[2].Value";
//        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
//        DataValue dv1 = new DataValue(new Variant(1000F));
//
//        try {
//            System.out.println("sent command to: " + identifier);
//            System.out.println(client.writeValue(node, dv1));
//            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
//            System.out.println(client.readValue(node));
//            System.out.println("**********************************");
//            System.out.println("");
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private static void productID(UaClient client) {
//        String identifier = "Command.Parameter[1].Value";
//        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
//        DataValue dv1 = new DataValue(new Variant(3.0F));
//
//        try {
//            System.out.println("sent command to: " + identifier);
//            System.out.println(client.writeValue(node, dv1));
//            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
//            System.out.println(client.readValue(node));
//            System.out.println("**********************************");
//            System.out.println("");
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private static void batchID(UaClient client) {
//        String identifier = "Command.Parameter[0].Value";
//        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
//        DataValue dv1 = new DataValue(new Variant(new Float(50)));
//
//        try {
//            System.out.println("sent command to: " + identifier);
//            System.out.println(client.writeValue(node, dv1));
//            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
//            System.out.println(client.readValue(node));
//            System.out.println("**********************************");
//            System.out.println("");
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private static void machSpeed(UaClient client) {
//        String identifier = "Command.MachSpeed";
//        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
//        DataValue dv1 = new DataValue(new Variant(100F));
//
//        try {
//            System.out.println("sent command to: " + identifier);
//            System.out.println(client.writeValue(node, dv1));
//            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
//            System.out.println(client.readValue(node));
//            System.out.println("**********************************");
//            System.out.println("");
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private static void startMachine(UaClient client) {
//        String identifier = "Command.CntrlCmd";
//        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
//        DataValue dv1 = new DataValue(new Variant(2));
//
//        try {
//            System.out.println("sent command to: " + identifier);
//            System.out.println(client.writeValue(node, dv1));
//            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
//            System.out.println(client.readValue(node));
//            System.out.println("**********************************");
//            System.out.println("");
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private static void reset(UaClient client) {
//        String identifier = "Command.CntrlCmd";
//        NodeId node = new NodeId(6, "::Program:Cube." + identifier);
//        DataValue dv1 = new DataValue(new Variant(1));
//
//        try {
//            System.out.println("sent command to: " + identifier);
//            System.out.println(client.writeValue(node, dv1));
//            client.writeValue(new NodeId(6, "::Program:Cube.Command.CmdChangeRequest"), new DataValue(new Variant(true)));
//            System.out.println(client.readValue(node));
//            System.out.println("**********************************");
//            System.out.println("");
//        } catch (ServiceException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (StatusException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//</editor-fold>

    private static MachineConnection connection = new MachineConnection("192.168.1.2", "sdu","1234");

    public Test() throws FileNotFoundException {
    }

    public static void main(String[] args) throws ServiceException, StatusException, InterruptedException, FileNotFoundException {
        sleep(100);

        Test.testAllMethods();

        Test.startMachine();

        connection.subscribeToCurrentState(new SubscribtiontestState(new Test()));
    }


    public void printProduction() throws ServiceException, StatusException, InterruptedException, IOException {

        System.out.println("Number of produced products "+connection.readNumberOfProducedProducts());
        sleep(100);
        System.out.println("Number of defective products "+connection.readNumberOfDefectiveProducts());
        printToFile();
        sleep(100);

        Test.startMachine();
    }

    private static void startMachine() throws ServiceException, StatusException, InterruptedException {


        numberOfRuns++;
        if(numberOfRuns%5==0){
            machineSpeed -=50;
        }
        connection.setMachineSpeed(machineSpeed);
        sleep(100);

        connection.setControlCommand(1);
        sleep(100);

        connection.setProductIDForNextBatch(0);
        sleep(100);

        connection.setAmountInNextBatch(200);
        sleep(100);

        connection.setBatchIDForNextBatch(600);
        sleep(1000);

        connection.setControlCommand(2);
        sleep(100);
    }

    private static void testAllMethods() throws ServiceException, StatusException {

        System.out.println("All read methods: "+
                "\nDefective: "+
        connection.readNumberOfDefectiveProducts()+
                "\nProduced: "+
        connection.readNumberOfProducedProducts()+
                "\nCurrent Batch ID: "+
        connection.readBatchIDCurrent()+
                "\nCurrent Product ID: "+
        connection.readCurrentProductID()+
                "\nCurrent state: "+
        connection.readCurrentState()+
                "\nHumidity: "+
        connection.readHumidity()+
                "\nTemperature: "+
        connection.readTemperature()+
                "\nVibration: "+
        connection.readVibration()+
                "\nStop Reason Value: "+
        connection.readStopReasonValue()+
                "\nStop Reason ID: "+
        connection.readStopReasonID()+
                "\nProducts in Batch: "+
        connection.readProductsInBatch()+
                "\nMachine speed: "+
        connection.readMachineSpeedCurrent());



    }

    private void printToFile() throws IOException, ServiceException, StatusException {
        System.out.println("penis");

        File file = new File("DataPilsner.txt");
        PrintWriter output22 = new PrintWriter(new FileOutputStream(file,true));
        String strin1 = "" + connection.readNumberOfDefectiveProducts();
        String strin2 = "" +  machineSpeed;
        output22.println(strin1+", " +strin2);
        output22.close();

    }
}

