package gluecode;

import communication.CommunicationFacade;
import communication.ISQLCommunicationFacade;
import communication.sqlcommunication.SQLCommunicationFacade;
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
        ERPOutFacade.getInstance().injectSQLFacade(new SQLCommunicationFacade());
        CommunicationFacade communicationFacade = new CommunicationFacade();
        mesOutFacade.injectCommunicationFacade(communicationFacade);

        MESFacade mesFacade = new MESFacade();
        erpOutFacade.injectCommunicationFacade(mesFacade);

        ERPFacade erpFacade = new ERPFacade();
        guiOutFacade.injectCommunicationFacade(erpFacade);

        SemesterProject3.main(args);
    }
}
