package logic.mes.pid;

import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IProductionOrder;
import logic.mes.mesacquantiance.IPIDFacade;
import acquantiance.IStorageReadable;

public class PIDFacade implements IPIDFacade {

    private static PIDFacade pidFacade;

    public static PIDFacade getInstance(){
        if(pidFacade == null)
        {
            pidFacade = new PIDFacade();
        }

        return pidFacade;
    }

    private PIDType pid;

    public PIDFacade(){
        this.pid = new SimplePID();
    }

    /**
     * Method for querying the PID for the next order
     * @param storage IStorageReadable for the processing plant
     * @param machinespecification IMachineSpecificationReadable for the machine the order is targeted for.
     * @return IOrder or null if the storage is full
     */
    @Override
    public IProductionOrder getOrder(IStorageReadable storage, IMachineSpecificationReadable machinespecification) {
        return this.pid.getIPIDOrder(storage,machinespecification);
    }

    @Override
    public void setPIDType(IPIDType type){

        switch (type){
            case SIMPLE:
                this.pid = new SimplePID();
        }
    }


}

