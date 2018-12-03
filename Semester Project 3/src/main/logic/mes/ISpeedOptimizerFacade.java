package logic.mes;

import acquantiance.ProductTypeEnum;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import logic.mes.speedoptimizer.OptimizerType;

public interface ISpeedOptimizerFacade {

    double optimize(ISpeedOptimizable specs, WeightedObservedPoints data, double cost, double sell, ProductTypeEnum type);

    void setOptimizer(OptimizerType type);

    OptimizerType getoptimizer();
}
