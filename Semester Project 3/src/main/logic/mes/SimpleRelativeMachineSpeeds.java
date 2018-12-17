package logic.mes;

import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IMesMachine;
import logic.mes.mesacquantiance.IRelativeMachineSpeeds;

import java.util.*;

public class SimpleRelativeMachineSpeeds implements IRelativeMachineSpeeds {

    private Map<String, Map<ProductTypeEnum, Float>> relativeSpeeds;
    private Map<String, Map<ProductTypeEnum, Float>> speeds;

    private int numberOfUpdates;

    public SimpleRelativeMachineSpeeds() {
        this.relativeSpeeds = new HashMap<>();
        this.speeds = new HashMap<>();

        this.numberOfUpdates = 0;
    }

    @Override
    public void addMachine(IMesMachine machine){
        Map<ProductTypeEnum, Float> productSpeeds = new HashMap<>();

        for (ProductTypeEnum typeEnum : ProductTypeEnum.values()) {
            productSpeeds.put(typeEnum, machine.getMachineSpecificationReadable().getOptimalSpeed(typeEnum));
        }

        this.speeds.put(machine.getMachineID(), productSpeeds);

        this.calculateRelativeSpeeds();
    }

    @Override
    public void removeMachine(String machineName){
        this.speeds.remove(machineName);

        this.calculateRelativeSpeeds();
    }

    @Override
    public List<String> getMostEffectiveMachines(ProductTypeEnum type){

        Map<String, Float> unsortedMachines = new HashMap<>();

        for (Map.Entry<String, Map<ProductTypeEnum, Float>> machine : this.relativeSpeeds.entrySet()) {
            unsortedMachines.put(machine.getKey(), machine.getValue().get(type));
        }

        List<String> sortedMachines = new ArrayList<>();

        while (unsortedMachines.size() > 0) {
            float maxValue = 0;
            String nextMachine = "";
            for (Map.Entry<String, Float> unsortedMachine : unsortedMachines.entrySet()) {

                if (unsortedMachine.getValue()>maxValue){
                    nextMachine = unsortedMachine.getKey();
                    maxValue = unsortedMachine.getValue();
                }

            }
            sortedMachines.add(nextMachine);
            unsortedMachines.remove(nextMachine);
        }

        return sortedMachines;
    }

    @Override
    public Float getMostEffectiveProduct(String machineName, ProductTypeEnum type){
        return this.relativeSpeeds.get(machineName).get(type);
    }

    @Override
    public void update(String machineName, ProductTypeEnum type, float speed){
        this.numberOfUpdates++;

        this.speeds.get(machineName).put(type, speed);

        if (numberOfUpdates%1==0){
            this.calculateRelativeSpeeds();
        }
    }

    private void calculateRelativeSpeeds() {
        Map<ProductTypeEnum, Float> totalProductSpeed = new HashMap<>();

        for (Map.Entry<String, Map<ProductTypeEnum, Float>> machine : this.speeds.entrySet()) {
            for (Map.Entry<ProductTypeEnum, Float> typeSpeed : machine.getValue().entrySet()) {
                if (totalProductSpeed.get(typeSpeed.getKey())!=null) {
                    Float semiTotal = totalProductSpeed.get(typeSpeed.getKey());
                    totalProductSpeed.put(typeSpeed.getKey(), semiTotal+typeSpeed.getValue());
                } else {
                    totalProductSpeed.put(typeSpeed.getKey(), typeSpeed.getValue());
                }
            }
        }

        for (Map.Entry<String, Map<ProductTypeEnum, Float>> machine : this.speeds.entrySet()) {
            for (Map.Entry<ProductTypeEnum, Float> productSpeed : machine.getValue().entrySet()) {
                Float value = (productSpeed.getValue()/totalProductSpeed.get(productSpeed.getKey()));
                if (this.relativeSpeeds.containsKey(machine.getKey())) {
                    this.relativeSpeeds.get(machine.getKey()).put(productSpeed.getKey(), value);
                } else {
                    this.relativeSpeeds.put(machine.getKey(), new HashMap<>());
                    this.relativeSpeeds.get(machine.getKey()).put(productSpeed.getKey(), value);
                }
            }
        }


        //<editor-fold desc="Old Table">
        /*for (Map.Entry<String, Map<ProductTypeEnum, Float>> machine : this.speeds.entrySet()) {
            for (Map.Entry<ProductTypeEnum, Float> productSpeed : machine.getValue().entrySet()) {

                float denominator = 0;

                for (String machineID : speeds.keySet()) {
                    denominator += this.speeds.get(machineID).get(productSpeed.getKey());
                }

                for (ProductTypeEnum type : ProductTypeEnum.values()) {
                    denominator += this.speeds.get(machine.getKey()).get(type);
                }

                float value = productSpeed.getValue();
                float relativeValue;

                if (denominator > value){
                    relativeValue = value/(denominator-value);
                } else {
                    relativeValue = 1;
                }

                if (this.relativeSpeeds.containsKey(machine.getKey())) {
                    this.relativeSpeeds.get(machine.getKey()).put(productSpeed.getKey(), relativeValue);
                } else {
                    this.relativeSpeeds.put(machine.getKey(), new HashMap<>());
                    this.relativeSpeeds.get(machine.getKey()).put(productSpeed.getKey(), relativeValue);
                }
            }
        }*/
        //</editor-fold>

    }
}
