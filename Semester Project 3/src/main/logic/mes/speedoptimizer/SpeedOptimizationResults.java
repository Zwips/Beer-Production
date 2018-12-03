package logic.mes.speedoptimizer;

public class SpeedOptimizationResults {

    private double speedOptimal;
    private double speedHigh;
    private double speedlow;

    private double[] parameters;
    private double squareR;

    public SpeedOptimizationResults(double speedOptimal, double speedHigh, double speedlow, double[] parameters, double squareR) {
        this.speedOptimal = speedOptimal;
        this.speedHigh = speedHigh;
        this.speedlow = speedlow;
        this.parameters = parameters;
        this.squareR = squareR;
    }

    public double getSpeedOptimal() {
        return speedOptimal;
    }

    public double getSpeedHigh() {
        return speedHigh;
    }

    public double getSpeedlow() {
        return speedlow;
    }

    public double[] getParameters() {
        return parameters;
    }

    public double getSquareR() {
        return squareR;
    }
}
