package logic.mes.mesacquantiance;

import acquantiance.ProductTypeEnum;
import acquantiance.IErrorRateDataPoint;
import logic.mes.speedoptimizer.OptimizerType;

import java.util.List;

public interface ISpeedOptimizerFacade {

    double optimize(ISpeedOptimizable specs, List<IErrorRateDataPoint> data, double cost, double sell, ProductTypeEnum type);

    void setOptimizer(OptimizerType type);

    OptimizerType getoptimizer();
}
