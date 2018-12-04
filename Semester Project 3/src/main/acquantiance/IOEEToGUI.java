package acquantiance;

import java.util.Date;
import java.util.Map;

public interface IOEEToGUI {
    float getOEEValue();
    Map<String,Long> getStatistics();
    Map<Date, String> getTimeOfChangeMap();



}
