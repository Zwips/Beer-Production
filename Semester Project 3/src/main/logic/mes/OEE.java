package logic.mes;

import acquantiance.IOEEToGUI;

import java.util.Map;

public class OEE implements IOEEToGUI {
    private float oEEValue;
    private Map<String, Long>statisticsMap;

    @Override
    public float getOEEValue() {
        return oEEValue;
    }

    @Override
    public Map<String, Long> getStatistics() {
        return statisticsMap;
    }

    public void setoEEValue(float oEEValue) {
        this.oEEValue = oEEValue;
    }

    public void setStatisticsMap(Map<String, Long> statisticsMap) {
        this.statisticsMap = statisticsMap;
    }
}
