package systemTest;

import Acquantiance.IERPFacade;
import communication.CommunicationFacade;
import gui.GUIOutFacade;
import logic.erp.ERPFacade;
import logic.erp.ERPOutFacade;
import logic.mes.MESFacade;
import logic.mes.MESOutFacade;

public class ERPLevelInitializer {

    public static IERPFacade glue(){
        ERPOutFacade erpOutFacade = ERPOutFacade.getInstance();
        MESOutFacade mesOutFacade = MESOutFacade.getInstance();
        GUIOutFacade guiOutFacade = GUIOutFacade.getInstance();

        CommunicationFacade communicationFacade = new CommunicationFacade();
        mesOutFacade.injectCommunicationFacade(communicationFacade);
        MESFacade mesFacade = new MESFacade();
        erpOutFacade.injectCommunicationFacade(mesFacade);

        ERPFacade erpFacade = new ERPFacade();
        guiOutFacade.injectCommunicationFacade(erpFacade);

        return erpFacade;
    }
}
