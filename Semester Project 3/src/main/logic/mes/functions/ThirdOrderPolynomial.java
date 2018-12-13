package logic.mes.functions;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;

public class ThirdOrderPolynomial implements ParametricUnivariateFunction {

    @Override
    public double value(double x, double... parameters) {
        double value = parameters[0]+parameters[1]*x+parameters[2]*x*x+parameters[3]*x*x*x;

        return value;
    }

    @Override
    public double[] gradient(double x, double... parameters) {

        double[] gradient = new double[4];

        gradient[0] = 1;
        gradient[1] = x;
        gradient[2] = x*x;
        gradient[3] = x*x*x;

        return gradient;
    }
}
