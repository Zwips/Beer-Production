package acquantiance;

public interface IStorage {
    int getCurrentAmount(ProductTypeEnum type);
    void setCurrentAmount(int amount, ProductTypeEnum type);
    int getTargetAmount(ProductTypeEnum type);
    void setTargetAmount(int amount, ProductTypeEnum type);
    String getFactoryID();
    void setFactoryID(String factoryID);
}
