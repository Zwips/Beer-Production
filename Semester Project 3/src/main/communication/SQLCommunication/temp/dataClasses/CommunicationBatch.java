package communication.SQLCommunication.temp.dataClasses;

import Acquantiance.IBatch;
import Acquantiance.ProductTypeEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommunicationBatch implements IBatch {

    private int batchID;
    private ProductTypeEnum productType;
    private int produced;
    private int discarded;
    private Map amountOfTimeInDifferentStates;
    private Map<Date, Float> temperatures;
    private Map<Date, Float> humidity;
    private Map<Date, Float> vibrations;
    public CommunicationBatch() {
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }

    public void setDiscarded(int discarded) {
        this.discarded = discarded;
    }

    public void setAmountOfTimeInDifferentStates(Map amountOfTimeInDifferentStates) {
        this.amountOfTimeInDifferentStates = amountOfTimeInDifferentStates;
    }

    public void setTemperatures(Map temperatures) {
        this.temperatures = temperatures;
    }

    public void setHumidity(Map humidity) {
        this.humidity = humidity;
    }

    public void setVibrations(Map vibrations) {
        this.vibrations = vibrations;
    }

    @Override
    public int getBatchID() {
        return this.batchID;
    }

    @Override
    public ProductTypeEnum getProductType() {
        return this.productType;
    }

    @Override
    public int getTotalProduced() {
        return this.produced;
    }

    @Override
    public int getTotalDiscarded() {
        return this.discarded;
    }

    @Override
    public Map getAmountOfTimeInDifferentStates() {
        return this.amountOfTimeInDifferentStates;
    }

    @Override
    public Map getTemperatures() {
        return this.temperatures;
    }

    @Override
    public Map getHumidity() {
        return this.humidity;
    }

    @Override
    public Map getVibrations() {
        return this.vibrations;
    }
}
