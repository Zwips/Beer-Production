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
import machineConnection.command.*;
import machineConnection.status.*;
import org.opcfoundation.ua.builtintypes.NodeId;

import static java.lang.Thread.sleep;

/**
 *
 * @author HCHB
 */
public class MachineConnection implements IMachineConnection {
    
    private String identifier="::Program:Cube.";
    private UaClient client;

    public MachineConnection(String address, String userID, String password){
        Connection connection = new Connection();
        this.client = connection.getConnection(address, password, userID);     // getConnection(address,password,userID);
    }

    //<editor-fold desc="Admin Commands">
    @Override
    public float readCurrentProductID() throws ServiceException, StatusException {
        IAdmin admin = new Admin();

        CurrentProductType prod = admin.getCurrentProductType(identifier);
        return prod.readCurrentProductID(client);
    }

    @Override
    public int readNumberOfDefectiveProducts() throws ServiceException, StatusException {
        IAdmin admin = new Admin();

        DefectiveProducts prod = admin.getDefectiveProducts(identifier);
        return prod.readNumberOfDefectiveProducts(client);
    }

    @Override
    public int  readNumberOfProducedProducts() throws ServiceException, StatusException {
        IAdmin admin = new Admin();

        ProducedProducts prod = admin.getProducedProducts(identifier);
        return prod.readNumberOfProducedProducts(client);
    }

    @Override
    public int readStopReasonID() throws ServiceException, StatusException {
        IAdmin admin = new Admin();

        StopReasonID prod = admin.getStopReasonId(identifier);
        return prod.readStopReasonID(client);
    }

    @Override
    public int readStopReasonValue() throws ServiceException, StatusException {
        IAdmin admin = new Admin();

        StopReasonValue prod = admin.getStopReasonValue(identifier);
        return prod.readStopReasonValue(client);
    }
    //</editor-fold>

    //<editor-fold desc="Subscriptions">
    @Override
    public void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException {
        IStatus status = new Status();

        NodeId node = status.getTemperature(identifier).getNode();

        SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);
    }

    @Override
    public void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException {
        IStatus status = new Status();

        NodeId node = status.getCurrentState(identifier).getNode();

        SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);
    }

    @Override
    public void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException {
        IStatus status = new Status();

        NodeId node = status.getVibration(identifier).getNode();

        SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);

    }

    @Override
    public void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException {
        IStatus status = new Status();

        NodeId node = status.getHumidity(identifier).getNode();

        SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);
    }

    @Override
    public void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException {
        IAdmin admin = new Admin();

        NodeId node = admin.getStopReasonId(identifier).getNode();

        SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);
    }
    //</editor-fold>

    //<editor-fold desc="Machine Commands">
    @Override
    public boolean setAmountInNextBatch(float value) throws ServiceException, StatusException {
        ICommand command = new Command();

        Amount amount = command.getAmount(identifier);

        amount.setAmountInNextBatch(client, value);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = amount.readAmountInNextBatch(client);

        if (readValue == value){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean setBatchIDForNextBatch(float value) throws ServiceException, StatusException {
        ICommand command = new Command();

        BatchID batchID = command.getBatchId(identifier);

        batchID.setBatchIDForNextBatch(client, value);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = batchID.readBatchIDForNextBatch(client);

        if (readValue == value){
            return true;
        }
        else{
            return false;
        }    }

    @Override
    public boolean setControlCommand(int value) throws ServiceException, StatusException {
        ICommand command = new Command();

        Control control = command.getControl(identifier);

        control.setControlCommand(client, value);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = control.readControlCommand(client);

        if (readValue == value){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean setMachineSpeed(float value) throws ServiceException, StatusException {
        ICommand command = new Command();

        MachineSpeed machineSpeed = command.getMachineSpeed(identifier);

        machineSpeed.setMachineSpeed(client, value);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = machineSpeed.readMachineSpeed(client);

        if (readValue == value){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean setProductIDForNextBatch(float value) throws ServiceException, StatusException {
        ICommand command = new Command();

        ProductID productId = command.getProductId(identifier);

        productId.setProductIDForNextBatch(client, value);
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = productId.readProductIDForNextBatch(client);

        if (readValue == value){
            return true;
        }
        else{
            return false;
        }

    }
    //</editor-fold>

    //<editor-fold desc="Status Commands">
    @Override
    public float readProductsInBatch() throws ServiceException, StatusException {
        IStatus status = new Status();

        BatchAmountCurrent prod = status.getBatchAmountCurrent(identifier);
        return prod.readProductsInBatch(client);
    }

    @Override
    public float readBatchIDCurrent() throws ServiceException, StatusException {
        IStatus status = new Status();

        BatchIDCurrent prod = status.getBatchIdCurrent(identifier);
        return prod.readBatchIDCurrent(client);
    }

    @Override
    public float readCurrentState() throws ServiceException, StatusException {
        IStatus status = new Status();

        CurrentState prod = status.getCurrentState(identifier);
        return prod.readCurrentState(client);
    }

    @Override
    public float readHumidity() throws ServiceException, StatusException {
        IStatus status = new Status();

        Humidity prod = status.getHumidity(identifier);
        return prod.readHumidity(client);    }

    @Override
    public float readMachineSpeedCurrent() throws ServiceException, StatusException {
        IStatus status = new Status();

        MachineSpeedCurrent prod = status.getMachineSpeedCurrent(identifier);
        return prod.readMachineSpeedCurrent(client);
    }

    @Override
    public float readTemperature() throws ServiceException, StatusException {
        IStatus status = new Status();

        Temperature prod = status.getTemperature(identifier);
        return prod.readTemperature(client);    }

    @Override
    public float readVibration() throws ServiceException, StatusException {
        IStatus status = new Status();

        Vibration prod = status.getVibration(identifier);
        return prod.readVibration(client);    }
    //</editor-fold>
}