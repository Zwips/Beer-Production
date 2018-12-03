package systemTest.DummyClasses;

import acquantiance.ProductTypeEnum;
import logic.mes.IMachineSpecificationReadable;

public class DummyMachineSpecification implements IMachineSpecificationReadable {

    @Override
    public float getOptimalSpeed(ProductTypeEnum productType){
        switch (productType) {
            case PILSNER:
                return 599;
            case WHEAT:
                return 300;
            case IPA:
                return 150;
            case STOUT:
                return 200;
            case ALE:
                return 100;
            case ALCOHOLFREE:
                return 125;
            default:
                return 100;
        }
    }

}
