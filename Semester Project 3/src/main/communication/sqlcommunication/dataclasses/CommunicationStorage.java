package communication.sqlcommunication.dataclasses;

import acquantiance.IStorage;
import acquantiance.ProductTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class CommunicationStorage implements IStorage {
    private Map<ProductTypeEnum, Integer[]> storage;
    private String factoryID;

    public CommunicationStorage(){
        storage = new HashMap<>();
        for (ProductTypeEnum type: ProductTypeEnum.values()) {
            storage.put(type, new Integer[2]);
        }
    }


    @Override
    public int getCurrentAmount(ProductTypeEnum type) {
        return storage.get(type)[0];
    }

    @Override
    public void setCurrentAmount(int amount, ProductTypeEnum type) {
        storage.get(type)[0] = amount;
    }

    @Override
    public int getTargetAmount(ProductTypeEnum type) {
        return storage.get(type)[1];
    }

    @Override
    public void setTargetAmount(int amount, ProductTypeEnum type) {
        storage.get(type)[1] = amount;
    }

    @Override
    public String getFactoryID() {
        return factoryID;
    }

    @Override
    public void setFactoryID(String factoryID) {
        this.factoryID = factoryID;
    }
}
