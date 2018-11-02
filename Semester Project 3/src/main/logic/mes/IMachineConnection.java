/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.mes;

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;

/**
 *
 * @author HCHB
 */
public interface IMachineConnection {


    //read status commands
    float readCurrentProductID() throws ServiceException, StatusException;
    int readNumberOfDefectiveProducts() throws ServiceException, StatusException;
    int  readNumberOfProducedProducts() throws ServiceException, StatusException;
    int readStopReasonID() throws ServiceException, StatusException;
    int readStopReasonValue() throws ServiceException, StatusException;

    //subscribe creations
    void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException;
    void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException;
    void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException;
    void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException;
    void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException;

    //set machine commands
    boolean setAmountInNextBatch(float value) throws ServiceException, StatusException;
    boolean setBatchIDForNextBatch(float value) throws ServiceException, StatusException;
    boolean setControlCommand(int value) throws ServiceException, StatusException;
    boolean setMachineSpeed(float value) throws ServiceException, StatusException;
    boolean setProductIDForNextBatch(float value) throws ServiceException, StatusException;

    //read status commands
    float readProductsInBatch() throws ServiceException, StatusException;
    float readBatchIDCurrent() throws ServiceException, StatusException;
    float readCurrentState() throws ServiceException, StatusException;
    float readHumidity() throws ServiceException, StatusException;
    float readMachineSpeedCurrent() throws ServiceException, StatusException;
    Float readTemperature() throws ServiceException, StatusException;
    Float readVibration() throws ServiceException, StatusException;

    boolean isConnected();
    void disconnect();


}
