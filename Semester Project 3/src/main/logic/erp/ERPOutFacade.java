package logic.erp;

import Acquantiance.IMESFacade;
import Acquantiance.IMesMachine;

public class ERPOutFacade {

    private static ERPOutFacade instance = null;
    private IMESFacade mesFacade;

    private ERPOutFacade(){
    }

    public static ERPOutFacade getInstance(){
        if (ERPOutFacade.instance == null){
            ERPOutFacade.instance = new ERPOutFacade();
        }

        return ERPOutFacade.instance;
    }

    public void injectCommunicationFacade(IMESFacade mesFacade){
        this.mesFacade = mesFacade;
    }

    IMesMachine createMachine(String name, String address, String userID, String password){
        return mesFacade.createMachine(name,address, userID, password);
    }

    boolean isConnected(IMesMachine machine)
    {
        return machine.isConnected();
    }


}
