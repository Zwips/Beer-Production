package logic.mes.pid;

import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IProductionOrder;
import acquantiance.IStorageReadable;

public class SimplePID implements PIDType {

    public SimplePID() {
    }

    @Override
    public IProductionOrder getIPIDOrder(IStorageReadable storage, IMachineSpecificationReadable machineSpecification){
        PIDOrder pidOrder = new PIDOrder();
        ProductTypeEnum productType = findProductType(storage);

        if(productType==null){
            return null;
        }

        float productionSpeed = chooseMachineSpeed(productType, machineSpecification);

        pidOrder.setProductTypeEnum(productType);
        pidOrder.setProductionSpeed(productionSpeed);
        pidOrder.setAmount(calculateAmount(productionSpeed, productType, storage));

        return pidOrder;
    }

    private int calculateAmount(float productionSpeed, ProductTypeEnum productType, IStorageReadable storage) {

        if ((int) productionSpeed < storage.getTargetAmount(productType) - storage.getCurrentAmount(productType)) {
            return (int) productionSpeed;
        }

        return storage.getTargetAmount(productType)-storage.getCurrentAmount(productType);
    }

    private float chooseMachineSpeed(ProductTypeEnum productType, IMachineSpecificationReadable machineSpecification){
        return machineSpecification.getOptimalSpeed(productType);
    }

    private ProductTypeEnum findProductType(IStorageReadable storage){
        double max = 0;
        ProductTypeEnum productType = null;

        for (ProductTypeEnum typeEnum : ProductTypeEnum.values()) {
            double currentAmount = storage.getCurrentAmount(typeEnum);
            double targetAmount = storage.getTargetAmount(typeEnum);
            double storagePercent = currentAmount/targetAmount;

            double typeWeight = getRelativeSpeed(typeEnum)*(1-storagePercent);
            if( typeWeight>max){
                productType = typeEnum;
                max = typeWeight;
            }
        }

        return productType;
    }

    private double getRelativeSpeed(ProductTypeEnum productType){
        //TODO move to table object in ProcessingPlant
        return 1.0;
    }


}
