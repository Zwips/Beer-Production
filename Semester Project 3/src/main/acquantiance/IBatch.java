package acquantiance;

import java.util.Date;
import java.util.Map;

public interface IBatch {

    int getBatchID();
    ProductTypeEnum getProductType();
    int getTotalProduced();
    int getTotalDiscarded();
    Map getAmountOfTimeInDifferentStates();
    Map<Date,Float> getTemperatures();
    Map<Date,Float> getHumidity();
    Map<Date,Float> getVibrations();
}
