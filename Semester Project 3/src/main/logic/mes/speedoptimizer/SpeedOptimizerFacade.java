package logic.mes.speedoptimizer;

import acquantiance.ProductTypeEnum;
import acquantiance.IErrorRateDataPoint;
import logic.mes.mesacquantiance.ISpeedOptimizable;
import logic.mes.mesacquantiance.ISpeedOptimizerFacade;
import logic.mes.mesacquantiance.IErrorFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.List;

public class SpeedOptimizerFacade implements ISpeedOptimizerFacade {

    private Optimizer optimizer;

    public SpeedOptimizerFacade(){
        this.optimizer = new PriceOptimizer();
    }

    @Override
    public double optimize(ISpeedOptimizable specs, List<IErrorRateDataPoint> data, double cost, double sell, ProductTypeEnum type) {
        IErrorFunction function = specs.getErrorFunction(type);

        WeightedObservedPoints weightedData =  this.convertData(data);

        SpeedOptimizationResults results = this.optimizer.optimize(function, weightedData, cost, sell);

        if (results.getSquareR() < 0.5){

            double[] parameters = function.getParameters();

            for (int i = 0; i < parameters.length; i++) {
                parameters[i] *= -1;
            }

            function.setParameters(parameters);
            SpeedOptimizationResults results2 = this.optimizer.optimize(function, weightedData, cost, sell);

            if (results.getSquareR() < results2.getSquareR()){
                results = results2;
            }
        }

        specs.setErrorFunctionParameters(results.getParameters(), type);
        specs.setProductionsSpeeds(results.getSpeedlow(), results.getSpeedOptimal(), results.getSpeedHigh(), type);


        return results.getSquareR();
    }

    private WeightedObservedPoints convertData(List<IErrorRateDataPoint> data) {

        WeightedObservedPoints weightedData = new WeightedObservedPoints();

        for (IErrorRateDataPoint dataPoint : data) {
            weightedData.add(dataPoint.getSpeed(), dataPoint.getDefective()/dataPoint.getBatchSize());
        }

        return weightedData;
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
