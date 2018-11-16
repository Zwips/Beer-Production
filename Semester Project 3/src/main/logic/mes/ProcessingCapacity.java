package logic.mes;

import Acquantiance.ProductTypeEnum;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ProcessingCapacity {


    private TreeMap<Date, Map<ProductTypeEnum, Integer>> capacity;
    private Map<ProductTypeEnum, Double> productionSpeeds;

    void prune(){
        Date now = new Date();
        for (Date date : capacity.keySet()) {
            if (date.before(now)) {
                capacity.remove(date);
            }
        }
    }

}
