package systemTest;

import Acquantiance.IERPFacade;
import communication.CommunicationFacade;
import gui.GUIOutFacade;
import logic.erp.ERPFacade;
import logic.erp.ERPOutFacade;
import logic.mes.MESFacade;
import logic.mes.MESOutFacade;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class ERPLevelInitializer {

    public static IERPFacade glue(){
        File file = new File("Simulation/start.bat");
        Process process;

        try {String[] command = { "cmd.exe", "/C", "Start", file.getAbsolutePath() };
            Runtime runtime = Runtime.getRuntime();
            process  = runtime.exec(command);
            process.waitFor();

            process.destroy();
            process.destroyForcibly();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
