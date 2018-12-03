package logic.mes.functions;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;

public class LinearFunction implements ParametricUnivariateFunction {

    @Override
    public double value(double x, double... parameters) {
        double value = parameters[0]+parameters[1]*x;

        return value;
    }

    @Override
    public double[] gradient(double x, double... parameters) {

        double[] gradient = new double[2];

        gradient[0] = 1;
        gradient[1] = x;

        return gradient;
    }
}
