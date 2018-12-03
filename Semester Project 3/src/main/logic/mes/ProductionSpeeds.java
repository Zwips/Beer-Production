package logic.mes;

public class ProductionSpeeds {

    private double low;
    private double optimal;
    private double high;

    public ProductionSpeeds(double low, double optimal, double high) {
        this.low = low;
        this.optimal = optimal;
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOptimal() {
        return optimal;
    }

    public void setOptimal(double optimal) {
        this.optimal = optimal;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }
}
