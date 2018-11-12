
package communication.machineConnection;

import Acquantiance.ProductTypeEnum;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.SQLCommunicationFacade;
import logic.mes.MachineSpecifications;

import java.io.*;

import static java.lang.Thread.sleep;

public class Test {
    static int numberOfRuns = 5;
    static ProductTypeEnum productType = ProductTypeEnum.IPA;

    static MachineSpecifications specs = new MachineSpecifications();
    static int machineSpeed = (int)specs.getOptimalSpeed(productType);

    //private static MachineConnection connection = new MachineConnection("192.168.1.2", "sdu","1234");
    private static MachineConnection connection = new MachineConnection("10.112.254.165", "sdu","1234");

    public Test() throws FileNotFoundException {
    }

    public static void main(String[] args) throws ServiceException, StatusException, InterruptedException, FileNotFoundException {
        sleep(100);

        Test.testAllMethods();

        Test.startMachine();

        connection.subscribeToCurrentState(new SubscriptionTestState(new Test()));
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
            machineSpeed -=25;
        }
        connection.setMachineSpeed(machineSpeed);
        sleep(100);

        connection.setControlCommand(1);
        sleep(100);

        connection.setProductIDForNextBatch(specs.getProductTypeCode(productType));
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
        ISQLCommunicationFacade sql = new SQLCommunicationFacade();
        sql.logDefectives("speedTest", connection.readNumberOfDefectiveProducts(), connection.readProductsInBatch(), machineSpeed, productType);
    }
}

