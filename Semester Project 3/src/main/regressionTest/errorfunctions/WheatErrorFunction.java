package regressionTest.errorfunctions;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;

public class WheatErrorFunction implements ParametricUnivariateFunction {

    @Override
    public double value(double x, double... parameters) {

        double value = parameters[0]+parameters[1]*x;

        return value;
    }

    @Override
    public double[] gradient(double x, double... parameters) {

        double[] gradient = new double[3];

        gradient[0] = 1;
        gradient[1] = x;

        return gradient;
    }
}
