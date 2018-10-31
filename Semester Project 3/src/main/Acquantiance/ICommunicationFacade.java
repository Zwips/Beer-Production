package Acquantiance;

import communication.ISQLCommunicationFacade;

import java.util.Date;

public interface ICommunicationFacade {
    void logBatch(String machineID,  int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID);
    void logVibration(float value, Date timestamp, int batchID);
    void logHumidity(float value, Date timestamp, int batchID);
}
