package logic.mes.mesacquantiance;

import acquantiance.ProductTypeEnum;

public interface IStorageReadable {
    int getCurrentAmount(ProductTypeEnum type);
    int getTargetAmount(ProductTypeEnum type);
}
