package logic.mes.speedoptimizer;

import acquantiance.ProductTypeEnum;
import logic.mes.ISpeedOptimizable;
import logic.mes.ISpeedOptimizerFacade;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import logic.mes.IErrorFunction;

public class SpeedOptimizerFacade implements ISpeedOptimizerFacade {

    private Optimizer optimizer;

    public SpeedOptimizerFacade(){
        this.optimizer = new PriceOptimizer();
    }

    @Override
    public double optimize(ISpeedOptimizable specs, WeightedObservedPoints data, double cost, double sell, ProductTypeEnum type) {
        IErrorFunction function = specs.getErrorFunction(type);

        SpeedOptimizationResults results = this.optimizer.optimize(function, data, cost, sell);

        if (results.getSquareR() < 0.5){

            double[] parameters = function.getParameters();

            for (int i = 0; i < parameters.length; i++) {
                parameters[i] *= -1;
            }

            function.setParameters(parameters);
            SpeedOptimizationResults results2 = this.optimizer.optimize(function, data, cost, sell);

            if (results.getSquareR() < results2.getSquareR()){
                results = results2;
            }
        }

        specs.setErrorFunctionParameters(results.getParameters(), type);
        specs.setProductionsSpeeds(results.getSpeedlow(), results.getSpeedOptimal(), results.getSpeedHigh(), type);


        return results.getSquareR();
    }

    @Override
    public void setOptimizer(OptimizerType type) {
        switch (type) {
            case PRICE:
                this.optimizer = new PriceOptimizer();
                break;
        }
    }

    @Override
    public OptimizerType getoptimizer() {
        if (this.optimizer instanceof PriceOptimizer){
            return OptimizerType.PRICE;
        } else{
            return null;
        }
    }

}
