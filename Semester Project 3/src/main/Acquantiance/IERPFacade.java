package Acquantiance;

import java.util.Date;

public interface IERPFacade {
    boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority);

    boolean addMachine( String name, String address, String userID, String password);
    boolean addMachine(String processingPantID, String name, String address, String userID, String password);
    void addProcessingPlant(String plantID);

    }