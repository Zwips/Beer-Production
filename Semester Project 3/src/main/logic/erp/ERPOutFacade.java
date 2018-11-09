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

    IMesMachine createMachine(String machineName, String IPAddress, String userID, String password){
        return mesFacade.createMachine(machineName,IPAddress, userID, password);
    }

    boolean isConnected(IMesMachine machine)
    {
        return machine.isConnected();
    }

    int getNextOrderID(){
        return mesFacade.getNextOrderID();
    }

    int getNextBatchID(){
        return mesFacade.getNextBatchID();
    }

}
