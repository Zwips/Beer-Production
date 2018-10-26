package logic.mes;

import java.util.HashMap;

public class MachineSpecifications {

    private HashMap<String,Integer> commandNumbers;
    private HashMap<Integer,String[]> allowedCommands;

    MachineSpecifications(){
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

    float getOptimalSpeed(int productType){
        switch (productType) {
            case 0:
                return 599;

            case 1:
                return 300;

            case 2:
                return 150;

            case 3:
                return 200;

            case 4:
                return 100;

            case 5:
                return 125;

            default:
                return 100;
        }
    }

    float maxSpeed(int productType) {
        switch (productType) {
            case 0:
                return 600;

            case 1:
                return 300;

            case 2:
                return 150;

            case 3:
                return 200;

            case 4:
                return 100;

            case 5:
                return 125;

            default:
                return 100;
        }
    }
}
