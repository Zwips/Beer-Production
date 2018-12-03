package logic.mes;

import acquantiance.ProductTypeEnum;

public interface IStorageReadable {
    int getCurrentAmount(ProductTypeEnum type);
    int getTargetAmount(ProductTypeEnum type);
}
