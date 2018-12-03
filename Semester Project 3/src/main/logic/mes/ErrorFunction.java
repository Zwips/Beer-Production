package logic.mes;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;

public class ErrorFunction implements IErrorFunction {

    private ParametricUnivariateFunction function;
    private double[] parameters;
    private double speedMax;
    private double latestRSquare;

    public ErrorFunction(ParametricUnivariateFunction function, double[] parameters, double speedMax) {
        this.function = function;
        this.parameters = parameters;
        this.speedMax = speedMax;
        this.latestRSquare = 0;
    }

    public double getLatestRSquare() {
        return latestRSquare;
    }

    public void setLatestRSquare(double latestRSquare) {
        this.latestRSquare = latestRSquare;
    }

    public ParametricUnivariateFunction getFunction() {
        return function;
    }

    public double[] getParameters() {
        return parameters;
    }

    public double getSpeedMax() {
        return speedMax;
    }

    public void setParameters(double[] parameters) {
        this.parameters = parameters;
    }
}
