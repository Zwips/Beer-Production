package systemTest.DummyClasses;

import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IStorageReadable;

import java.util.HashMap;
import java.util.Map;


public class DummyStorage implements IStorageReadable {
    private Map<ProductTypeEnum, Integer[]> storage;
    private String factoryID;

    public DummyStorage(){
        storage = new HashMap<>();
        for (ProductTypeEnum type: ProductTypeEnum.values()) {
            storage.put(type, new Integer[2]);
            storage.get(type)[0] = 0;
            storage.get(type)[1] = 0;
        }
    }


    @Override
    public int getCurrentAmount(ProductTypeEnum type) {
        return storage.get(type)[0];
    }

    public void setCurrentAmount(int amount, ProductTypeEnum type) {
        storage.get(type)[0] = amount;
    }

    @Override
    public int getTargetAmount(ProductTypeEnum type) {
        return storage.get(type)[1];
    }

    public void setTargetAmount(int amount, ProductTypeEnum type) {
        storage.get(type)[1] = amount;
    }

    public String getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(String factoryID) {
        this.factoryID = factoryID;
    }
}
