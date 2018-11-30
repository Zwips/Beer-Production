package systemTest;

import acquantiance.IERPFacade;
import acquantiance.IMESFacade;
import communication.CommunicationFacade;
import gui.GUIOutFacade;
import logic.erp.ERPFacade;
import logic.erp.ERPOutFacade;
import logic.mes.MESFacade;
import logic.mes.MESOutFacade;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MESLevelInitializer {

    public static IMESFacade glue(){
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
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        CommunicationFacade communicationFacade = new CommunicationFacade();
        MESOutFacade.getInstance().injectCommunicationFacade(communicationFacade);
        MESFacade mesFacade = new MESFacade();


        return mesFacade;
    }
}
