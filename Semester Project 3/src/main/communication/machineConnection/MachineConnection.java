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
    public float readCurrentProductID() throws ServiceException {
        IAdmin admin = new Admin();

        int amountOfTries = 0;
        CurrentProductType prod = admin.getCurrentProductType(identifier);
        do {
            try {
                return prod.readCurrentProductID(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);
        return -1;
    }

    @Override
    public int readNumberOfDefectiveProducts() throws ServiceException {
        IAdmin admin = new Admin();
        int amountOfTries = 0;
        DefectiveProducts prod = admin.getDefectiveProducts(identifier);
        do{
            try {
                return prod.readNumberOfDefectiveProducts(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);
        return -1;

    }

    @Override
    public int  readNumberOfProducedProducts() throws ServiceException {
        IAdmin admin = new Admin();
        int amountOfTries = 0;
        ProducedProducts prod = admin.getProducedProducts(identifier);
        do{
            try {
                return prod.readNumberOfProducedProducts(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }

            amountOfTries++;
        } while (amountOfTries < 5);
        return -1;
    }

    @Override
    public int readStopReasonID() throws ServiceException {
        IAdmin admin = new Admin();
        int amountOfTries = 0;
        StopReasonID prod = admin.getStopReasonId(identifier);
        do{
            try {
                return prod.readStopReasonID(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }

            amountOfTries++;
        } while (amountOfTries < 5);
        return -1;
    }

    @Override
    public int readStopReasonValue() throws ServiceException {
        IAdmin admin = new Admin();
        int amountOfTries = 0;
        StopReasonValue prod = admin.getStopReasonValue(identifier);
        do{
            try {
                return prod.readStopReasonValue(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);
        return -1;
    }
    //</editor-fold>

    //<editor-fold desc="Subscriptions">
    @Override
    public void subscribeToTemperature(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();
        NodeId node = status.getTemperature(identifier).getNode();
        int amountOfTries = 0;
        createSubscription(dataChangeCatcher, node, amountOfTries);

    }

    @Override
    public void subscribeToCurrentState(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();

        NodeId node = status.getCurrentState(identifier).getNode();
        int amountOfTries = 0;
        createSubscription(dataChangeCatcher, node, amountOfTries);
    }

    @Override
    public void subscribeToVibration(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();

        NodeId node = status.getVibration(identifier).getNode();
        int amountOfTries = 0;
        createSubscription(dataChangeCatcher, node, amountOfTries);
    }

    @Override
    public void subscribeToHumidity(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IStatus status = new Status();

        NodeId node = status.getHumidity(identifier).getNode();
        int amountOfTries = 0;
        createSubscription(dataChangeCatcher, node, amountOfTries);
    }

    @Override
    public void subscribeToStopReasonID(IDataChangeCatcher dataChangeCatcher) throws ServiceException {
        IAdmin admin = new Admin();

        NodeId node = admin.getStopReasonId(identifier).getNode();
        int amountOfTries = 0;
        createSubscription(dataChangeCatcher, node, amountOfTries);
    }

    private void createSubscription(IDataChangeCatcher dataChangeCatcher, NodeId node, int amountOfTries) throws ServiceException {
        do{
            try {
                SubscriptionCreator.createSubscription(client,node,dataChangeCatcher);
                break;
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5) ;
    }
    //</editor-fold>

    //<editor-fold desc="Machine Commands">
    @Override
    public boolean setAmountInNextBatch(float value) throws ServiceException {
        ICommand command = new Command();

        Amount amount = command.getAmount(identifier);
        int amountOfTries = 0;
        do {
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
            amountOfTries++;
        }while (amountOfTries < 5);

        return false;

    }

    @Override
    public boolean setBatchIDForNextBatch(float value) throws ServiceException {
        ICommand command = new Command();

        BatchID batchID = command.getBatchId(identifier);
        float readValue = 0;
        int amountOfTries = 0;
        do {
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
            amountOfTries++;
        } while(amountOfTries < 5);
        return false;
    }

    @Override
    public boolean setControlCommand(int value) throws ServiceException {
        ICommand command = new Command();

        Control control = command.getControl(identifier);
        int amountOfTries = 0;
        do {
            try {
                control.setControlCommand(client, value);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int readValue = 0;
            try {
                readValue = control.readControlCommand(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }

            if (readValue == value) {
                return true;
            }
            amountOfTries++;

        }while(amountOfTries <5);

        return false;

    }

    @Override
    public boolean setMachineSpeed(float value) throws ServiceException {
        ICommand command = new Command();

        MachineSpeed machineSpeed = command.getMachineSpeed(identifier);

        int amountOfTries = 0;
        do {
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
            amountOfTries++;
        }while(amountOfTries <5);
        return false;

    }

    @Override
    public boolean setProductIDForNextBatch(float value) throws ServiceException {
        ICommand command = new Command();

        ProductID productId = command.getProductId(identifier);
        int amountOfTries = 0;

        do {
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
            amountOfTries++;
        }while (amountOfTries < 5);
        return false;


    }
    //</editor-fold>

    //<editor-fold desc="Status Commands">
    @Override
    public float readProductsInBatch() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            BatchAmountCurrent prod = status.getBatchAmountCurrent(identifier);
            try {
                return prod.readProductsInBatch(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);
        return -1;
    }

    @Override
    public float readBatchIDCurrent() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            BatchIDCurrent prod = status.getBatchIdCurrent(identifier);
            try {
                return prod.readBatchIDCurrent(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);

        return -1;
    }

    @Override
    public float readCurrentState() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            CurrentState prod = status.getCurrentState(identifier);
            try {
                return prod.readCurrentState(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);

        return -1;
    }

    @Override
    public float readHumidity() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            Humidity prod = status.getHumidity(identifier);
            try {
                return prod.readHumidity(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);

        return -1;
    }

    @Override
    public float readMachineSpeedCurrent() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            MachineSpeedCurrent prod = status.getMachineSpeedCurrent(identifier);
            try {
                return prod.readMachineSpeedCurrent(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);

        return -1;
    }

    @Override
    public float readTemperature() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            Temperature prod = status.getTemperature(identifier);
            try {
                return prod.readTemperature(client);
            } catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);

        return -1;
    }

    @Override
    public float readVibration() throws ServiceException {
        IStatus status = new Status();
        int amountOfTries = 0;
        do{
            Vibration prod = status.getVibration(identifier);
            try {
                return prod.readVibration(client);
            }  catch (StatusException e) {
                e.printStackTrace();
            }
            amountOfTries++;
        } while (amountOfTries < 5);

        return -1;
    }

    @Override
    public boolean isConnected(){
        return client.isConnected();
    }

    @Override
    public void disConnect() {
        client.disconnect();
    }
    //</editor-fold>
}