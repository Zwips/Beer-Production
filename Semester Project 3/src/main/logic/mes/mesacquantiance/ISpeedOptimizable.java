package logic.mes.mesacquantiance;

import acquantiance.ProductTypeEnum;

public interface ISpeedOptimizable {

    IErrorFunction getErrorFunction(ProductTypeEnum type);

    void setErrorFunctionParameters(double[] parameters, ProductTypeEnum type);

    void setProductionsSpeeds(double speedLow, double speedOptimal, double speedHigh, ProductTypeEnum type);
}
