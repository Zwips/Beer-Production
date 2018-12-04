package logic.mes;

import acquantiance.IOEEToGUI;

import java.util.Date;
import java.util.Map;

public class OEE implements IOEEToGUI {
    private float oEEValue;
    private Map<String, Long>statisticsMap;
    private Map<Date, String> timeOfChangeMap;

    @Override
    public float getOEEValue() {
        return oEEValue;
    }

    @Override
    public Map<String, Long> getStatistics() {
        return statisticsMap;
    }

    @Override
    public Map<Date, String> getTimeOfChangeMap() {
        return timeOfChangeMap;
    }

    public void setoEEValue(float oEEValue) {
        this.oEEValue = oEEValue;
    }

    public void setStatisticsMap(Map<String, Long> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }

    public void setTimeOfChangeMap(Map<Date, String> timeOfChangeMap){
        this.timeOfChangeMap = timeOfChangeMap;
    }
}
