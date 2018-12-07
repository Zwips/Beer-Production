package acquantiance;

public interface IStorageReadable {

    int getCurrentAmount(ProductTypeEnum type);
    int getTargetAmount(ProductTypeEnum type);
    String getFactoryID();

}
