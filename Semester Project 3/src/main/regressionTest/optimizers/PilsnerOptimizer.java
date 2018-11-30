package regressionTest.optimizers;

import org.apache.commons.math3.fitting.SimpleCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import regressionTest.errorfunctions.PilsnerErrorFunction;

public class PilsnerOptimizer {


    public double profitOptimalSpeed(double[] guess, WeightedObservedPoints data, double cost, double sell, double speedMax){
        PilsnerErrorFunction function = new PilsnerErrorFunction();

        SimpleCurveFitter fitter = SimpleCurveFitter.create(function, guess);
        double[] results = fitter.fit(data.toList());

        double speed = 0;
        double optimalSpeed = 0;
        double optimalProfit = 0;
        double profit;

        while(speed<speedMax){
            profit = speed*((1-function.value(speed, results)/200)*sell-cost);

            if (optimalProfit<profit){
                optimalSpeed = speed;
                optimalProfit = profit;
            }
            speed += 1;
        }

        return optimalSpeed;
    }
}
