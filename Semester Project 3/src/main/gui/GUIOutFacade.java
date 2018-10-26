package gui;

import Acquantiance.IERPFacade;
import logic.erp.ERPFacade;

import java.util.Date;

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

    public boolean addOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){

        return erpFacade.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);

    }

}
