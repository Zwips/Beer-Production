
package communication.machineConnection;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;

import java.io.*;

import static java.lang.Thread.sleep;

public class Test {
    static int machineSpeed = 575;
    static int numberOfRuns = 0;

    private static MachineConnection connection = new MachineConnection("192.168.1.2", "sdu","1234");
    //private static MachineConnection connection = new MachineConnection("127.0.0.1:4840", "sdu","1234");

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

