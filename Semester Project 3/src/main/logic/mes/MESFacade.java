package logic.mes;

import Acquantiance.*;

import java.util.HashMap;
import java.util.List;

public class MESFacade implements IMESFacade {

    @Override
    public IMesMachine createMachine(String machineName, String IPAddress, String userID, String password) {
        return new Machine(machineName, IPAddress, userID, password);
    }

    @Override
    public int getNextOrderID() {
        return MESOutFacade.getInstance().getNextOrderID();
    }

    @Override
    public int getNextBatchID() {
        return MESOutFacade.getInstance().getNextBatchID();
    }

    @Override
    public List<IProductionOrder> getPendingOrders() {
        return MESOutFacade.getInstance().getPendingOrders();
    }

    @Override
    public HashMap<String, List<IMachineConnectionInformation>> getMachines() {
        return MESOutFacade.getInstance().getMachines();
    }

    @Override
    public void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password) {
        MESOutFacade.getInstance().InsertMachine(factoryID, machineID, machine_IP, userID, password);
    }


}
