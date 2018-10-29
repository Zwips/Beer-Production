package Acquantiance;

import java.util.HashMap;

public interface IBatch {

    int getBatchID();
    ProductTypeEnum getProductType();
    int getTotalProduced();
    int getTotalDiscarded();
    HashMap getAmountOfTimeInDifferentStates();
    HashMap getTemperatures();
    HashMap getHumidity();
    HashMap getVibrations();
}
