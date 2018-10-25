package logic.mes;


import Acquantiance.IDataChangeCatcher;
import Acquantiance.IMachineConnection;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import communication.machineConnection.MachineConnection;

public class Machine implements IMachine, Runnable{

    private String name;
    private String address;
    private String userID;
    private String password;
    private IMachineConnection iMachineConnection;

    public Machine(String name, String address, String userID, String password){
        this.name = name;
        this.address = address;
        this.userID = userID;
        this.password = password;
        createMachineConnection();
    }

    private void createMachineConnection(){
        iMachineConnection = new MachineConnection(address, userID, password);
    };

    @Override
    public void reconnectMachine() {
        createMachineConnection();
    }

    @Override
    public boolean executeOrder(IProductionOrder order) {
        return false;
    }

    @Override
    public boolean abandonOrder() {
        return false;
    }

    @Override
    public String getMachineID() {
        return name;
    }

    @Override
    public float readCurrentProductID() throws ServiceException {
        return 0;
    }


    @Override
    public int readNumberOfDefectiveProducts() throws ServiceException {
        return 0;
    }

    @Override
    public int readNumberOfProducedProducts() throws ServiceException {
        return 0;
    }

    @Override
    public int readStopReasonID() throws ServiceException {
        return 0;
    }

    @Override
    public int readStopReasonValue() throws ServiceException {
        return 0;
    }

    @Override
    public void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException {

    }

    @Override
    public void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException {

    }

    @Override
    public void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException {

    }

    @Override
    public void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException {

    }

    @Override
    public void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException {

    }

    @Override
    public float readProductsInBatch() throws ServiceException {
        return 0;
    }

    @Override
    public float readBatchIDCurrent() throws ServiceException {
        return 0;
    }

    @Override
    public float readCurrentState() throws ServiceException {
        return 0;
    }

    @Override
    public float readHumidity() throws ServiceException {
        return 0;
    }

    @Override
    public float readMachineSpeedCurrent() throws ServiceException {
        return 0;
    }

    @Override
    public float readTemperature() throws ServiceException {
        return 0;
    }

    @Override
    public float readVibration() throws ServiceException {
        return 0;
    }

    @Override
    public void run() {

    }

    //skal flyttes til persistens
    /*private removeMachine(String address){

    }*/

}
