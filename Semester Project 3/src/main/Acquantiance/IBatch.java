package Acquantiance;

import java.util.HashMap;
import java.util.Map;

public interface IBatch {

    int getBatchID();
    ProductTypeEnum getProductType();
    int getTotalProduced();
    int getTotalDiscarded();
    Map getAmountOfTimeInDifferentStates();
    Map getTemperatures();
    Map getHumidity();
    Map getVibrations();
}
