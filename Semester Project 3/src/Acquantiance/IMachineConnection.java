/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquantiance;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;

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
    void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher);
    void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher);
    void subscribeToVibration(IDataChangeCatcher dataChangeCatcher);
    void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher);
    void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher);

    //set machine commands
    boolean setAmountInNextBatch(float value);
    boolean setBatchIDForNextBatch(float value);
    boolean setControlCommand(int value);
    boolean setMachineSpeed(float value);
    boolean setProductIDForNextBatch(float value);

    //read status commands
    float readProductsInBatch() throws ServiceException, StatusException;
    float readBatchIDCurrent() throws ServiceException, StatusException;
    float readCurrentState() throws ServiceException, StatusException;
    float readHumidity() throws ServiceException, StatusException;
    float readMachineSpeedCurrent() throws ServiceException, StatusException;
    float readTemperature() throws ServiceException, StatusException;
    float readVibration() throws ServiceException, StatusException;



    }
