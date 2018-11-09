package Acquantiance;

import java.util.Date;

public interface IERPFacade {
    boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority);

    boolean addMachine( String machineName, String address, String userID, String password);
    boolean addMachine(String processingPantID, String machineName, String address, String userID, String password);
    void addProcessingPlant(String plantID);
    void removeProcessingPlant(String plantID);

    boolean removeMachine(String processingPantID, String machineName);
    boolean removeMachine(String machineName);

    }