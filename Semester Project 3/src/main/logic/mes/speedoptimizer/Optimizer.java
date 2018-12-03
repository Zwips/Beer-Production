package logic.mes.speedoptimizer;

import org.apache.commons.math3.fitting.WeightedObservedPoints;
import logic.mes.IErrorFunction;

public interface Optimizer {

    SpeedOptimizationResults optimize(IErrorFunction function, WeightedObservedPoints data, double cost, double sell);

}
