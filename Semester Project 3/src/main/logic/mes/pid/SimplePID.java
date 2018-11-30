package logic.mes.pid;

import acquantiance.IStorage;
import acquantiance.ProductTypeEnum;
import logic.mes.IMachineSpecificationReadable;
import logic.mes.IOrder;
import logic.mes.IStorageReadable;

public class SimplePID {

    private IStorageReadable storage;
    private IMachineSpecificationReadable machineSpecification;

    public SimplePID(IStorageReadable storage, IMachineSpecificationReadable machineSpecification) {
        this.storage = storage;
        this.machineSpecification = machineSpecification;

    }
    public IOrder getIPIDOrder(){
        PIDOrder pidOrder = new PIDOrder();
        ProductTypeEnum productType = findProductType();
        if(productType==null){
            return null;
        }
        float productionSpeed = chooseMachineSpeed(productType);
        pidOrder.setProductTypeEnum(productType);
        pidOrder.setProductionSpeed(productionSpeed);
        pidOrder.setAmount(calculateAmount(productionSpeed,productType));
        return pidOrder;



    }
    private int calculateAmount(float productionSpeed, ProductTypeEnum productType) {

        if ((int) productionSpeed < storage.getTargetAmount(productType) - storage.getCurrentAmount(productType)) {


        return (int) productionSpeed;
    }
    return storage.getTargetAmount(productType)-storage.getCurrentAmount(productType);
    }

    private float chooseMachineSpeed(ProductTypeEnum productType){
        return machineSpecification.getOptimalSpeed(productType);

    }

    private ProductTypeEnum findProductType(){
        int max = 0;
        ProductTypeEnum productType = null;
        for (ProductTypeEnum typeEnum : ProductTypeEnum.values()) {
            double storagePercent = storage.getCurrentAmount(typeEnum)/storage.getTargetAmount(typeEnum);
         if( getRelativeSpeed(typeEnum)*(1-storagePercent)>max){
             productType = typeEnum;

         }

        }
        return productType;
    }
    private double getRelativeSpeed(ProductTypeEnum productType){
        return 1.0;
    }


}
