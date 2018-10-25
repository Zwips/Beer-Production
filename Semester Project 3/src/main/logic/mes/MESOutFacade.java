package logic.mes;

import Acquantiance.ICommunicationFacade;

public class MESOutFacade {

    private static MESOutFacade instance = null;
    private ICommunicationFacade communicationFacade;

    private MESOutFacade(){
    }

    public static MESOutFacade getInstance(){
        if (MESOutFacade.instance == null){
            MESOutFacade.instance = new MESOutFacade();
        }

        return MESOutFacade.instance;
    }

    public void injectCommunicationFacade(ICommunicationFacade communicationFacade){
        this.communicationFacade = communicationFacade;
    }

}
