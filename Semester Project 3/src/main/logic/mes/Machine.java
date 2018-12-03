package logic.mes;

/** Representing a machine in our system
 * @author Asmus
 * @param createMachineConnection Method for creating a connecting to a machine.
 * @param executeOrder Method for executing a order.
 * @param abandonOrder Method for dropping a order.
 * @param isConnected  Method for checking if a machine is connected.
 * @param subscribeToTemperature Method for creating a subscription to the temperature.
 * @param subscribeToCurrentState Method for creating a subscription to the current state.
 * @param subscribeToVibration Method for creating a subscription to the vibration.
 * @param subscribeToHumidity Method for creating a subscription to the Humidity
 * @param subscribeToStopReasonID Method for creating a subscription to StopReasonID
 * @param uploadBatchInfo Method for all information about a Batch.
 */

import acquantiance.IDataChangeCatcher;
import acquantiance.IMachineConnection;
import acquantiance.IProductionOrder;
import acquantiance.ProductTypeEnum;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IMesMachine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Thread.sleep;

public class Machine implements IMesMachine, acquantiance.IMesMachine, Runnable{

    private final String factoryID;
    private String name;
    private String IPAddress;
    private String userID;
    private String password;
    private IMachineConnection machineConnection;
    private MachineSpecifications specs;
    private int currentOrderID;

    public Machine(String name, String IPAddress, String userID, String password, String factoryID){
        this.name = name;
        this.IPAddress = IPAddress;
        this.userID = userID;
        this.password = password;
        createMachineConnection();
        specs = new MachineSpecifications();
        this.factoryID = factoryID;
        setMachineSubscriptions();
    }

    private void setMachineSubscriptions(){
        try {
            subscribeToCurrentState(new MachineCurrentMachineStateReporter(this));
            subscribeToCurrentState(new MachineOEE(this,factoryID));
            subscribeToHumidity(new MachineHumidityReporter(this,factoryID));
            subscribeToTemperature(new MachineTemperatureReporter(this,factoryID));
            subscribeToVibration(new MachineVibrationReporter(this,factoryID));
            subscribeToStopReasonID(new MachineStopReasonIdReporter(this));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void createMachineConnection(){
        machineConnection = MESOutFacade.getInstance().connectToMachine(IPAddress, userID, password);
    }

    @Override
    public void reconnectMachine() {
        createMachineConnection();
    }

    @Override
    public boolean executeOrder(IProductionOrder order, float batchId) {
        int amount = order.getAmount();
        ProductTypeEnum productType = order.getProductType();
        float speed = specs.getOptimalSpeed(productType);
        int currentState = -1;

        try {
            currentState = (int) machineConnection.readCurrentState();
        } catch (ServiceException | StatusException e) {
            e.printStackTrace();
        }

        try {
            while(!(currentState == 6 || currentState == -1)){
                switch(currentState){
                    case 2:
                        machineConnection.setControlCommand(1);
                        break;
                    case 4:
                        machineConnection.setAmountInNextBatch(amount);
                        sleep(100);
                        machineConnection.setProductIDForNextBatch(specs.getProductTypeCode(productType));
                        sleep(100);
                        machineConnection.setMachineSpeed(speed);
                        sleep(100);
                        machineConnection.setBatchIDForNextBatch(batchId);
                        sleep(100);
                        machineConnection.setControlCommand(2);
                        this.currentOrderID = order.getOrderID();
                        return true;
                    case 9:
                        machineConnection.setControlCommand(5);
                        break;
                    case 17:
                        machineConnection.setControlCommand(3);
                        break;
                }
                sleep(500);
                currentState = (int)machineConnection.readCurrentState();
            }
        } catch (ServiceException | StatusException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean abandonOrder() {
        throw new NotImplementedException();
    }

    @Override
    public String getMachineID() {
        return name;
    }

    @Override
    public IMachineSpecificationReadable getMachineSpecificationReadable() {
        return specs;
    }

    @Override
    public boolean isConnected() {
        return machineConnection.isConnected();
    }

    @Override
    public float readCurrentProductID() throws ServiceException {

        try {
            return machineConnection.readCurrentProductID();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public int readNumberOfDefectiveProducts() throws ServiceException {
        try {
            return machineConnection.readNumberOfDefectiveProducts();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;

    }

    @Override
    public int readNumberOfProducedProducts() throws ServiceException {
        try {
            return machineConnection.readNumberOfProducedProducts();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public int readStopReasonID() throws ServiceException {
        try {
            return machineConnection.readStopReasonID();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public int readStopReasonValue() throws ServiceException {
        try {
            return machineConnection.readStopReasonValue();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        try {
            machineConnection.subscribeToTemperature(dataChangeCatcher);
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }

    }

    @Override
    public void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        try {
            machineConnection.subscribeToCurrentState(dataChangeCatcher);
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        try {
            machineConnection.subscribeToVibration(dataChangeCatcher);
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    @Override
    public void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        try {
            machineConnection.subscribeToHumidity(dataChangeCatcher);
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    @Override
    public void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        try {
            machineConnection.subscribeToStopReasonID(dataChangeCatcher);
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    @Override
    public float readProductsInBatch() throws ServiceException {
        try {
            return machineConnection.readProductsInBatch();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public float readBatchIDCurrent() throws ServiceException {
        try {
            return machineConnection.readBatchIDCurrent();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public float readCurrentState() {
        try {
            return machineConnection.readCurrentState();
        } catch (StatusException|ServiceException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public float readHumidity() throws ServiceException {
        try {
            return machineConnection.readHumidity();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public float readMachineSpeedCurrent() throws ServiceException {
        try {
            return machineConnection.readMachineSpeedCurrent();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public float readTemperature() throws ServiceException {
        try {
            return machineConnection.readTemperature();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;

    }

    @Override
    public float readVibration() throws ServiceException {
        try {
            return machineConnection.readVibration();
        } catch (StatusException e) {
            try {
                sleep(1000);
                reconnectMachine();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public void run() {

    }

    void uploadBatchInfo() {
        float batchID = -1;
        int numberOfDefective = -1;
        float productsInBatch = -1;
        int currentProduct = -1;
        float machineSpeed = -1;

        try {
            batchID = readBatchIDCurrent();
            currentProduct = (int) readCurrentProductID();
            productsInBatch = readProductsInBatch();
            numberOfDefective = readNumberOfDefectiveProducts();
            machineSpeed = readMachineSpeedCurrent();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ProductTypeEnum product = this.specs.getProductType(currentProduct);

        MESOutFacade.getInstance().logDefective(getMachineID(), numberOfDefective, productsInBatch, machineSpeed, product);
    }

    @Override
    public int getCurrentOrderID() {
        return currentOrderID;
    }

    @Override
    public ProductTypeEnum getProductType(float productTypeID){
        return this.specs.getProductType(productTypeID);
    }
}
