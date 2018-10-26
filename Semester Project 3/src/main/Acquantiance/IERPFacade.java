package Acquantiance;

import java.util.Date;

public interface IERPFacade {
    boolean addOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority);


    }