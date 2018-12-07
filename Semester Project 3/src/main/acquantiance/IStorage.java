package acquantiance;

public interface IStorage extends IStorageReadable {

    void setCurrentAmount(int amount, ProductTypeEnum type);
    void setTargetAmount(int amount, ProductTypeEnum type);
    void setFactoryID(String factoryID);

}
