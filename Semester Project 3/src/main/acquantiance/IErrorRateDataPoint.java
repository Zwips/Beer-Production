package acquantiance;

import acquantiance.ProductTypeEnum;

public interface IErrorRateDataPoint {

    ProductTypeEnum getType();

    double getDefective();

    double getBatchSize();

    double getSpeed();
}
