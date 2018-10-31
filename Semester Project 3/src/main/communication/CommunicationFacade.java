package communication;

import Acquantiance.ICommunicationFacade;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.SQLCommunicationFacade;

import java.util.Date;

public class CommunicationFacade implements ICommunicationFacade {

    private ISQLCommunicationFacade sqlFacade;


    CommunicationFacade(){
        sqlFacade = new SQLCommunicationFacade(); // skal muligvis flyttes til glue
    }

    @Override
    public void logBatch(String machineId,  int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product) {
        sqlFacade.logBatch(machineId, numberOfDefective,productsInBatch,machineSpeed,product);
    }

    @Override
    public void logTemperature(float value, Date timestamp, int batchID) {

    }

    @Override
    public void logVibration(float value, Date timestamp, int batchID) {

    }

    @Override
    public void logHumidity(float value, Date timestamp, int batchID) {

    }
}
