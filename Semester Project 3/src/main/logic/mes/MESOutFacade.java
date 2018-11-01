package logic.mes;

import Acquantiance.ICommunicationFacade;
import Acquantiance.ProductTypeEnum;

import java.util.Date;

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

    public void logBatch(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product){
        communicationFacade.logDefectives(machineID, numberOfDefective, productsInBatch, machineSpeed, product);
    }

    public void logTemperature(float value, Date timestamp, int batchID) {

    }

    public void logVibration(float value, Date timestamp, int batchID) {

    }

    public void logHumidity(float value, Date timestamp, int batchID){

    }

}
