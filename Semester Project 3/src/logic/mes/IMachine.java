package logic.mes;

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;

public interface IMachine {

    //<editor-fold desc="commands to machine"

    void reconnectMachine();
    boolean executeOrder(IProductionOrder order);
    boolean abandonOrder();
    String getMachineID();


    //</editor-fold>


    //<editor-fold desc="commands copied from machine connection"
    //read status commands
    float readCurrentProductID() throws ServiceException;
    int readNumberOfDefectiveProducts() throws ServiceException;
    int  readNumberOfProducedProducts() throws ServiceException;
    int readStopReasonID() throws ServiceException;
    int readStopReasonValue() throws ServiceException;

    //subscribe creations
    void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException;
    void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException;
    void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException;
    void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException;
    void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException;

    //read status commands
    float readProductsInBatch() throws ServiceException;
    float readBatchIDCurrent() throws ServiceException;
    float readCurrentState() throws ServiceException;
    float readHumidity() throws ServiceException;
    float readMachineSpeedCurrent() throws ServiceException;
    float readTemperature() throws ServiceException;
    float readVibration() throws ServiceException;
    //</editor-fold>



}
