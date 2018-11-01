package glueCode;

import communication.CommunicationFacade;
import gui.GUIOutFacade;
import gui.SemesterProject3;
import logic.erp.ERPFacade;
import logic.erp.ERPOutFacade;
import logic.mes.MESFacade;
import logic.mes.MESOutFacade;

public class Starter {

    public static void main(String[] args) {
        ERPOutFacade erpOutFacade = ERPOutFacade.getInstance();
        MESOutFacade mesOutFacade = MESOutFacade.getInstance();
        GUIOutFacade guiOutFacade = GUIOutFacade.getInstance();

        CommunicationFacade communicationFacade = new CommunicationFacade();
        ERPFacade erpFacade = new ERPFacade();
        MESFacade mesFacade = new MESFacade();

        erpOutFacade.injectCommunicationFacade(mesFacade);
        mesOutFacade.injectCommunicationFacade(communicationFacade);
        guiOutFacade.injectCommunicationFacade(erpFacade);

        SemesterProject3.main(args);
    }
}