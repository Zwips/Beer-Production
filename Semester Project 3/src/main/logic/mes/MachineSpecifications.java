package logic.mes;

/** Represents the specifications for a machine.
 * @author Asmus
 */

import acquantiance.ProductTypeEnum;
import logic.mes.functions.ExponentialFunction;
import logic.mes.functions.LinearFunction;
import logic.mes.functions.ThirdOrderPolynomial;
import logic.mes.mesacquantiance.IErrorFunction;
import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.ISpeedOptimizable;

import java.util.HashMap;

public class MachineSpecifications implements ISpeedOptimizable, IMachineSpecificationReadable {

    private HashMap<String,Integer> commandNumbers;
    private HashMap<Integer,String[]> allowedCommands;
    private HashMap<ProductTypeEnum, ErrorFunction> errorFunctions;
    private HashMap<ProductTypeEnum, ProductionSpeeds> productionsSpeeds;

    final private float PILSNERMAXSPEED = 600;
    final private float WHEATMAXSPEED = 300;
    final private float IPAMAXSPEED = 150;
    final private float STOUTMAXSPEED = 200;
    final private float ALEMAXSPEED = 100;
    final private float ALCOHOLFREEMAXSPEED = 125;

    public MachineSpecifications(){
        commandNumbers = new HashMap<>();
        commandNumbers.put("",0);
        commandNumbers.put("Reset",1);
        commandNumbers.put("Start",2);
        commandNumbers.put("Stop",3);
        commandNumbers.put("Abort",4);
        commandNumbers.put("Clear",5);

        allowedCommands = new HashMap<>();
        allowedCommands.put(4, new String[]{"Start"});
        allowedCommands.put(6,new String[]{"Stop","Abort"});
        allowedCommands.put(17,new String[]{"Stop","Abort","Reset"});
        allowedCommands.put(9,new String[]{"Clear"});
        allowedCommands.put(2,new String[]{"Reset"});

        errorFunctions = new HashMap<>();
        double[] initialGuessPilsner = {0, 0.0013, 0.01};
        errorFunctions.put(ProductTypeEnum.PILSNER, new ErrorFunction(new ExponentialFunction(), initialGuessPilsner,600));
        double[] initialGuessAle = {0, 2, 0.2};
        errorFunctions.put(ProductTypeEnum.ALE, new ErrorFunction(new ExponentialFunction(), initialGuessAle,100));
        double[] initialGuessIPA = {0, 0.23, 0.2};
        errorFunctions.put(ProductTypeEnum.IPA, new ErrorFunction(new ExponentialFunction(), initialGuessIPA,150));
        double[] initialGuessStout = {102, -0.5, 0.005, -0.000022850983427439953};
        errorFunctions.put(ProductTypeEnum.STOUT, new ErrorFunction(new ThirdOrderPolynomial(), initialGuessStout,200));
        double[] initialGuessWheat = {0, 0};
        errorFunctions.put(ProductTypeEnum.WHEAT, new ErrorFunction(new LinearFunction(), initialGuessWheat, 300));
        double[] initialGuessAlcoholFree= {0, 30, 0.1};
        errorFunctions.put(ProductTypeEnum.ALCOHOLFREE, new ErrorFunction(new ExponentialFunction(), initialGuessAlcoholFree,125));

        productionsSpeeds = new HashMap<>();
        productionsSpeeds.put(ProductTypeEnum.PILSNER, new ProductionSpeeds(this.PILSNERMAXSPEED, this.PILSNERMAXSPEED, this.PILSNERMAXSPEED));
        productionsSpeeds.put(ProductTypeEnum.ALE, new ProductionSpeeds(this.ALEMAXSPEED, this.ALEMAXSPEED, this.ALEMAXSPEED));
        productionsSpeeds.put(ProductTypeEnum.IPA, new ProductionSpeeds(this.IPAMAXSPEED, this.IPAMAXSPEED, this.IPAMAXSPEED));
        productionsSpeeds.put(ProductTypeEnum.STOUT, new ProductionSpeeds(this.STOUTMAXSPEED, this.STOUTMAXSPEED, this.STOUTMAXSPEED));
        productionsSpeeds.put(ProductTypeEnum.WHEAT, new ProductionSpeeds(this.WHEATMAXSPEED, this.WHEATMAXSPEED, this.WHEATMAXSPEED));
        productionsSpeeds.put(ProductTypeEnum.ALCOHOLFREE, new ProductionSpeeds(this.ALCOHOLFREEMAXSPEED, this.ALCOHOLFREEMAXSPEED, this.ALCOHOLFREEMAXSPEED));
    }

    int getCommandNumber(String command){
        return this.commandNumbers.get(command);
    }

    String[] getAllowedCommands(int state){
        return allowedCommands.get(state);
    }

    float getMaxSpeed(ProductTypeEnum productType) {
        switch (productType) {
            case PILSNER:
                return this.PILSNERMAXSPEED;
            case WHEAT:
                return this.WHEATMAXSPEED;
            case IPA:
                return this.IPAMAXSPEED;
            case STOUT:
                return this.STOUTMAXSPEED;
            case ALE:
                return this.ALEMAXSPEED;
            case ALCOHOLFREE:
                return this.ALCOHOLFREEMAXSPEED;
            default:
                return 100;
        }
    }

    public float getProductTypeCode(ProductTypeEnum productType) {
        switch (productType) {
            case PILSNER:
                return 0;
            case WHEAT:
                return 1;
            case IPA:
                return 2;
            case STOUT:
                return 3;
            case ALE:
                return 4;
            case ALCOHOLFREE:
                return 5;
            default:
                return -1;
        }
    }

    public ProductTypeEnum getProductType(float productID){
        int currentProduct = (int)productID;

        switch (currentProduct) {
            case 0:
                return ProductTypeEnum.PILSNER;
            case 1:
                return ProductTypeEnum.WHEAT;
            case 2:
                return ProductTypeEnum.IPA;
            case 3:
                return ProductTypeEnum.STOUT;
            case 4:
                return ProductTypeEnum.ALE;
            default:
                return ProductTypeEnum.ALCOHOLFREE;
        }
    }

    public double getLowSpeed(ProductTypeEnum type){
        return this.productionsSpeeds.get(type).getLow();
    }

    @Override
    public float getOptimalSpeed(ProductTypeEnum type){
        return (float)this.productionsSpeeds.get(type).getOptimal();
    }

    public double getHighSpeed(ProductTypeEnum type){
        return this.productionsSpeeds.get(type).getHigh();
    }

    @Override
    public IErrorFunction getErrorFunction(ProductTypeEnum type){
        return this.errorFunctions.get(type);
    }

    @Override
    public void setErrorFunctionParameters(double[] parameters, ProductTypeEnum type){
        this.errorFunctions.get(type).setParameters(parameters);
    }

    @Override
    public void setProductionsSpeeds(double speedLow, double speedOptimal, double speedHigh, ProductTypeEnum type){
        this.productionsSpeeds.put(type, new ProductionSpeeds(speedLow, speedOptimal, speedHigh));
    }

}
