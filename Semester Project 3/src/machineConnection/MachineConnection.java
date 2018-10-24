/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package machineConnection;

import Acquantiance.IDataChangeCatcher;
import Acquantiance.IMachineConnection;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import machineConnection.admin.*;
import machineConnection.status.Status;

/**
 *
 * @author HCHB
 */
public class MachineConnection implements IMachineConnection {
    
    private String identifier="::Program:Cube.";
    private UaClient client;

    public MachineConnection(String address, String userID, String password){

        this.client = this.getConnection(address,password,userID);
    }

    //<editor-fold desc="Admin Commands">
    @Override
    public float readCurrentProductID() throws ServiceException, StatusException {
        Admin admin = new Admin();

        CurrentProductType prod = admin.getCurrentProductType(identifier);
        return prod.readCurrentProductID(client);
    }

    @Override
    public int readNumberOfDefectiveProducts() throws ServiceException, StatusException {
        Admin admin = new Admin();

        DefectiveProducts prod = admin.getDefectiveProducts(identifier);
        return prod.readNumberOfDefectiveProducts(client);
    }

    @Override
    public int  readNumberOfProducedProducts() throws ServiceException, StatusException {
        Admin admin = new Admin();

        ProducedProducts prod = admin.getProducedProducts(identifier);
        return prod.readNumberOfProducedProducts(client);
    }

    @Override
    public int readStopReasonID() throws ServiceException, StatusException {
        Admin admin = new Admin();

        StopReasonID prod = admin.getStopReasonId(identifier);
        return prod.readStopReasonID(client);
    }

    @Override
    public int readStopReasonValue() throws ServiceException, StatusException {
        Admin admin = new Admin();

        StopReasonValue prod = admin.getStopReasonValue(identifier);
        return prod.readStopReasonValue(client);
    }
    //</editor-fold>

    //<editor-fold desc="Subscriptions">
    @Override
    public void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) {
        IStatus status = new Status();

        SubscriptionCreator.createSubscription()

    }

    @Override
    public void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) {

    }

    @Override
    public void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) {

    }

    @Override
    public void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) {

    }

    @Override
    public void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) {

    }
    //</editor-fold>

    //<editor-fold desc="Machine Commands">
    @Override
    public boolean setAmountInNextBatch(float value) {
        return false;
    }

    @Override
    public boolean setBatchIDForNextBatch(float value) {
        return false;
    }

    @Override
    public boolean setControlCommand(int value) {
        return false;
    }

    @Override
    public boolean setMachineSpeed(float value) {
        return false;
    }

    @Override
    public boolean setProductIDForNextBatch(float value) {
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Status Commands">
    @Override
    public float readProductsInBatch() throws ServiceException, StatusException {
        return 0;
    }

    @Override
    public float readBatchIDCurrent() throws ServiceException, StatusException {
        return 0;
    }

    @Override
    public float readCurrentState() throws ServiceException, StatusException {
        return 0;
    }

    @Override
    public float readHumidity() throws ServiceException, StatusException {
        return 0;
    }

    @Override
    public float readMachineSpeedCurrent() throws ServiceException, StatusException {
        return 0;
    }

    @Override
    public float readTemperature() throws ServiceException, StatusException {
        return 0;
    }

    @Override
    public float readVibration() throws ServiceException, StatusException {
        return 0;
    }
    //</editor-fold>
}