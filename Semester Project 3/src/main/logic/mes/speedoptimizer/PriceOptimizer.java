package logic.mes.speedoptimizer;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.SimpleCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import logic.mes.IErrorFunction;

import java.util.List;

public class PriceOptimizer implements Optimizer {

    @Override
    public SpeedOptimizationResults optimize(IErrorFunction function, WeightedObservedPoints data, double cost, double sell) {

        ParametricUnivariateFunction functionType = function.getFunction();
        double[] guess = function.getParameters();
        double speedMax = function.getSpeedMax();

        SimpleCurveFitter fitter = SimpleCurveFitter.create(functionType, guess);
        double[] parameters = fitter.fit(data.toList());

        double speed = 0;
        double optimalSpeed = 0;
        double lowSpeed = 0;
        double highSpeed = 0;
        double optimalProfit = 0;
        double profit;

        while(speed<speedMax){
            profit = speed*((1-functionType.value(speed, parameters))*sell-cost);

            if (optimalProfit<profit){
                optimalSpeed = speed;
                optimalProfit = profit;
                highSpeed = speed;
            }

            if (speed >0 && functionType.value(speed, parameters)< 0.025){
                lowSpeed = speed;
            }

            if (profit > 0 && speed>optimalSpeed){
                highSpeed = optimalSpeed*(speed-optimalSpeed)/4;
            }

            speed += 1;
        }


        double squareR = this.squareR(functionType, data, parameters);

        SpeedOptimizationResults results = new SpeedOptimizationResults(optimalSpeed, highSpeed, lowSpeed, parameters,squareR);

        return results;
    }

    private double squareR(ParametricUnivariateFunction function, WeightedObservedPoints data, double[] guess){
        double sumY = 0;
        List<WeightedObservedPoint> dataList = data.toList();

        for (WeightedObservedPoint dataPoint : dataList) {
            sumY += dataPoint.getY();
        }

        double averageY = sumY/dataList.size();

        double sumResiduals = 0;
        double sumTotal = 0;

        for (WeightedObservedPoint point : dataList) {
            sumTotal += Math.pow((point.getY()-averageY),2);
            sumResiduals += Math.pow((point.getY()-function.value(point.getX(), guess)),2);
        }

        double squareR = 1.0-sumResiduals/sumTotal;

        return squareR;
    }

}
