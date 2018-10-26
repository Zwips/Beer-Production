package logic.mes;


import Acquantiance.IDataChangeCatcher;
import Acquantiance.IMesMachine;
import Acquantiance.IProductionOrder;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import communication.machineConnection.MachineConnection;

import static java.lang.Thread.sleep;

public class Machine implements IMachine, IMesMachine, Runnable{

    private String name;
    private String IPAddress;
    private String userID;
    private String password;
    private IMachineConnection machineConnection;
    private MachineSpecifications specs;

    public Machine(String name, String IPAddress, String userID, String password){
        this.name = name;
        this.IPAddress = IPAddress;
        this.userID = userID;
        this.password = password;
        createMachineConnection();
        specs = new MachineSpecifications();

    }

    private void createMachineConnection(){
        machineConnection = new MachineConnection(IPAddress, userID, password);
    };

    @Override
    public void reconnectMachine() {
        createMachineConnection();
    }

    @Override
    public boolean executeOrder(IProductionOrder order, float batchId) {
        int amount = order.getAmount();
        float productType = order.getProductType();
        float speed = specs.getOptimalSpeed((int) productType);
        int currentState = -1;
        try {
            currentState = (int) machineConnection.readCurrentState();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (StatusException e) {
            e.printStackTrace();
        }
        try {

            while(currentState != 6 || currentState != -1){
                switch(currentState){
                    case 2:
                        machineConnection.setControlCommand(1);
                        break;
                    case 4:
                        machineConnection.setAmountInNextBatch(amount);
                        sleep(100);
                        machineConnection.setProductIDForNextBatch(productType);
                        sleep(100);
                        machineConnection.setMachineSpeed(speed);
                        sleep(100);
                        machineConnection.setBatchIDForNextBatch(batchId);
                        sleep(100);
                        machineConnection.setControlCommand(2);
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

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
    public float readCurrentState() throws ServiceException {
        try {
            return machineConnection.readCurrentState();
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

    //skal flyttes til persistens
    /*private removeMachine(String address){

    }*/

}
