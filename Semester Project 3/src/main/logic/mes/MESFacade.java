package logic.mes;

import Acquantiance.IMESFacade;
import Acquantiance.IMesMachine;

public class MESFacade implements IMESFacade {
    @Override
    public IMesMachine createMachine(String name, String address, String userID, String password) {
        return new Machine(name, address, userID, password);
    }
}
