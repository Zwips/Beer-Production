package communication.sqlcommunication.dataclasses;

import acquantiance.ProductTypeEnum;
import acquantiance.IErrorRateDataPoint;

public class ErrorRateDataPoint implements IErrorRateDataPoint {

    private double defective;
    private double batchSize;
    private double speed;
    private ProductTypeEnum type;

    public ErrorRateDataPoint(int defective, int batchSize, double speed, ProductTypeEnum type) {
        this.defective = defective;
        this.batchSize = batchSize;
        this.speed = speed;
        this.type = type;
    }

    @Override
    public ProductTypeEnum getType() {
        return type;
    }

    @Override
    public double getDefective() {
        return defective;
    }

    @Override
    public double getBatchSize() {
        return batchSize;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
}
