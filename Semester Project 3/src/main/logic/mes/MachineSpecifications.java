package logic.mes;

import Acquantiance.ProductTypeEnum;

import java.util.HashMap;

public class MachineSpecifications {

    private HashMap<String,Integer> commandNumbers;
    private HashMap<Integer,String[]> allowedCommands;

    public MachineSpecifications(){
        commandNumbers = new HashMap<String, Integer>();
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
    }

    int getCommandNumber(String command){
        return this.commandNumbers.get(command);
    }

    String[] getAllowedCommands(int state){
        return allowedCommands.get(state);
    }

    float getOptimalSpeed(ProductTypeEnum productType){
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

    float maxSpeed(ProductTypeEnum productType) {
        switch (productType) {
            case PILSNER:
                return 600;
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

    public float getProductTypeCode(ProductTypeEnum productType){
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
}
