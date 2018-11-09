package logic.mes;

import Acquantiance.IMESFacade;
import Acquantiance.IMesMachine;

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


}
