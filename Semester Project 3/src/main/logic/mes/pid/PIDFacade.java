package logic.mes.pid;

import logic.mes.ProcessingPlant;
import logic.mes.SimpleRelativeMachineSpeeds;
import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IProductionOrder;
import logic.mes.mesacquantiance.IPIDFacade;
import acquantiance.IStorageReadable;
import logic.mes.mesacquantiance.IRelativeMachineSpeeds;

public class PIDFacade implements IPIDFacade {

    private static PIDFacade instance;

    private IRelativeMachineSpeeds speedTable;
    private PIDType pid;

    public PIDFacade(IRelativeMachineSpeeds speedTable){
        this.speedTable = speedTable;
        this.pid = new SimplePID(speedTable);
    }

    public static PIDFacade getInstance() {

        if (instance==null) {
            instance = new PIDFacade(new SimpleRelativeMachineSpeeds());
        }

        return instance;
    }

    /**
     * Method for querying the PID for the next order
     * @param storage IStorageReadable for the processing plant
     * @param machinespecification IMachineSpecificationReadable for the machine the order is targeted for.
     * @return IOrder or null if the storage is full
     */
    @Override
    public IProductionOrder getOrder(IStorageReadable storage, IMachineSpecificationReadable machinespecification, String machineID) {
        return this.pid.getIPIDOrder(storage, machinespecification, machineID);
    }

    @Override
    public void setPIDType(IPIDType type){

        switch (type){
            case SIMPLE:
                this.pid = new SimplePID(speedTable);
        }
    }


}

