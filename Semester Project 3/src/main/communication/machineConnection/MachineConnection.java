/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineConnection;

import Acquantiance.IDataChangeCatcher;
import logic.mes.IMachineConnection;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import communication.machineConnection.admin.*;
import communication.machineConnection.command.*;
import communication.machineConnection.status.*;
import org.opcfoundation.ua.builtintypes.NodeId;

import static java.lang.Thread.sleep;

/** Represents the connection to the machine
 * @author HCHB
 *
 */
public class MachineConnection implements IMachineConnection, AutoCloseable {

    private String identifier="::Program:Cube.";
    private UaClient client;

    public MachineConnection(String address, String userID, String password){
        Connection connection = new Connection();
        this.client = connection.getConnection(address, password, userID);     // getConnection(address,password,userID);
    }

    //<editor-fold desc="Admin Commands">
    @Override
    public float readCurrentProductID() throws ServiceException {
        IAdmin admin = new Admin();

        CurrentProductType prod = admin.getCurrentProductType(identifier);
        try {
            return prod.readCurrentProductID(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int readNumberOfDefectiveProducts() throws ServiceException {
        IAdmin admin = new Admin();
        DefectiveProducts prod = admin.getDefectiveProducts(identifier);
        try {
            return prod.readNumberOfDefectiveProducts(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public int  readNumberOfProducedProducts() throws ServiceException {
        IAdmin admin = new Admin();
        ProducedProducts prod = admin.getProducedProducts(identifier);
        try {
            return prod.readNumberOfProducedProducts(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int readStopReasonID() throws ServiceException {
        IAdmin admin = new Admin();
        StopReasonID prod = admin.getStopReasonId(identifier);
        try {
            return prod.readStopReasonID(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int readStopReasonValue() throws ServiceException {
        IAdmin admin = new Admin();
        StopReasonValue prod = admin.getStopReasonValue(identifier);
        try {
            return prod.readStopReasonValue(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }
        return -1;
    }
    //</editor-fold>

    //<editor-fold desc="Subscriptions">
    @Override
    public void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();
        NodeId node = status.getTemperature(identifier).getNode();
        createSubscription(dataChangeCatcher, node);

    }

    @Override
    public void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();

        NodeId node = status.getCurrentState(identifier).getNode();
        createSubscription(dataChangeCatcher, node);
    }

    @Override
    public void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();

        NodeId node = status.getVibration(identifier).getNode();
        createSubscription(dataChangeCatcher, node);
    }

    @Override
    public void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();

        NodeId node = status.getHumidity(identifier).getNode();
        createSubscription(dataChangeCatcher, node);
    }

    @Override
    public void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IAdmin admin = new Admin();

        NodeId node = admin.getStopReasonId(identifier).getNode();
        createSubscription(dataChangeCatcher, node);
    }

    private void createSubscription(IDataChangeCatcher dataChangeCatcher, NodeId node) throws ServiceException {
        try {
            SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);
        } catch (StatusException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Machine Commands">
    @Override
    public boolean setAmountInNextBatch(float value) throws ServiceException {
        ICommand command = new Command();

        Amount amount = command.getAmount(identifier);
        try {
            amount.setAmountInNextBatch(client, value);
            sleep(50);
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = 0;
        try {
            readValue = amount.readAmountInNextBatch(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        if (readValue == value) {
            return true;
        }

        return false;

    }

    @Override
    public boolean setBatchIDForNextBatch(float value) throws ServiceException {
        ICommand command = new Command();

        BatchID batchID = command.getBatchId(identifier);
        float readValue = 0;
        try {
            batchID.setBatchIDForNextBatch(client, value);
            sleep(50);
            readValue = batchID.readBatchIDForNextBatch(client);
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (readValue == value) {
            return true;
        }

        return false;
    }

    @Override
    public boolean setControlCommand(int value) throws ServiceException {
        ICommand command = new Command();

        Control control = command.getControl(identifier);
        try {
            control.setControlCommand(client, value);
        } catch (StatusException e) {
                e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean setMachineSpeed(float value) throws ServiceException {
        ICommand command = new Command();

        MachineSpeed machineSpeed = command.getMachineSpeed(identifier);

        try {
            machineSpeed.setMachineSpeed(client, value);
        } catch (StatusException e) {
            e.printStackTrace();
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = 0;
        try {
            readValue = machineSpeed.readMachineSpeed(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        if (readValue == value) {
            return true;
        }

        return false;

    }

    @Override
    public boolean setProductIDForNextBatch(float value) throws ServiceException {
        ICommand command = new Command();

        ProductID productId = command.getProductId(identifier);

        try {
            productId.setProductIDForNextBatch(client, value);
        } catch (StatusException e) {
            e.printStackTrace();
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float readValue = 0;
        try {
            readValue = productId.readProductIDForNextBatch(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        if (readValue == value) {
            return true;
        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Status Commands">
    @Override
    public float readProductsInBatch() throws ServiceException {
        IStatus status = new Status();
        BatchAmountCurrent prod = status.getBatchAmountCurrent(identifier);
        try {
            return prod.readProductsInBatch(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public float readBatchIDCurrent() throws ServiceException {
        IStatus status = new Status();
        BatchIDCurrent prod = status.getBatchIdCurrent(identifier);
        try {
            return prod.readBatchIDCurrent(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public float readCurrentState() throws ServiceException {
        IStatus status = new Status();
        CurrentState prod = status.getCurrentState(identifier);
        try {
            return prod.readCurrentState(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public float readHumidity() throws ServiceException {
        IStatus status = new Status();
        Humidity prod = status.getHumidity(identifier);
        try {
            return prod.readHumidity(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public float readMachineSpeedCurrent() throws ServiceException {
        IStatus status = new Status();

        MachineSpeedCurrent prod = status.getMachineSpeedCurrent(identifier);
        try {
            return prod.readMachineSpeedCurrent(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Float readTemperature() throws ServiceException {
        IStatus status = new Status();

        Temperature prod = status.getTemperature(identifier);
        try {
            return prod.readTemperature(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Float readVibration() throws ServiceException {
        IStatus status = new Status();

        Vibration prod = status.getVibration(identifier);
        try {
            return prod.readVibration(client);
        } catch (StatusException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public boolean isConnected(){
        return client.isConnected();
    }

    @Override
    public void disconnect() {
        client.disconnect();
    }
    //</editor-fold>

    @Override
    public void finalize(){
        this.disconnect();
    }

    @Override
    public void close() throws Exception {
        this.disconnect();
    }
}