package gui;

import Acquantiance.IERPFacade;

public class GUIOutFacade {

    private static GUIOutFacade instance = null;
    private IERPFacade erpFacade;

    private GUIOutFacade(){
    }

    public static GUIOutFacade getInstance(){
        if (GUIOutFacade.instance == null){
            GUIOutFacade.instance = new GUIOutFacade();
        }

        return GUIOutFacade.instance;
    }

    public void injectCommunicationFacade(IERPFacade erpFacade){
        this.erpFacade = erpFacade;
    }

}
