package logic.mes;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;

public interface IErrorFunction {

    double getLatestRSquare();
    ParametricUnivariateFunction getFunction();
    double[] getParameters();
    double getSpeedMax();
    void setParameters(double[] parameters);
}
