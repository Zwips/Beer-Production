package logic.erp;

import Acquantiance.IMESFacade;

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
}
