package Acquantiance;

import java.util.Date;
import java.util.Map;

public interface IOEE {
    float getOEE();
    Map<Date,String> getStateChangeMap();
    String getMachineID();
    String getFactoryID();

}
