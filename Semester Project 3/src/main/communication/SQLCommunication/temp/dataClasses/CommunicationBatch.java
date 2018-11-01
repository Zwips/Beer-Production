package communication.SQLCommunication.temp.dataClasses;

import Acquantiance.IBatch;
import Acquantiance.ProductTypeEnum;

import java.util.HashMap;

public class CommunicationBatch implements IBatch {

    private int batchID;
    private ProductTypeEnum productType;
    private int produced;
    private int discarded;
    private HashMap amountOfTimeInDifferentStates;
    private HashMap temperatures;
    private HashMap humidity;
    private HashMap vibrations;

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

    public void setAmountOfTimeInDifferentStates(HashMap amountOfTimeInDifferentStates) {
        this.amountOfTimeInDifferentStates = amountOfTimeInDifferentStates;
    }

    public void setTemperatures(HashMap temperatures) {
        this.temperatures = temperatures;
    }

    public void setHumidity(HashMap humidity) {
        this.humidity = humidity;
    }

    public void setVibrations(HashMap vibrations) {
        this.vibrations = vibrations;
    }

    @Override
    public int getBatchID() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductTypeEnum getProductType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTotalProduced() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTotalDiscarded() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashMap getAmountOfTimeInDifferentStates() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashMap getTemperatures() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashMap getHumidity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashMap getVibrations() {
        throw new UnsupportedOperationException();
    }
}
