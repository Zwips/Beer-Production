package logic.mes;

import acquantiance.IProcessingCapacity;
import acquantiance.ProductTypeEnum;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ProcessingCapacity implements IProcessingCapacity {


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
