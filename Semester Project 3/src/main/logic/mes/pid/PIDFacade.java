package logic.mes.pid;

import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IOrder;
import logic.mes.mesacquantiance.IPIDFacade;
import logic.mes.mesacquantiance.IStorageReadable;

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

    private PIDFacade(){
        this.pid = new SimplePID();
    }

    /**
     * Method for querying the PID for the next order
     * @param storage IStorageReadable for the processing plant
     * @param machinespecification IMachineSpecificationReadable for the machine the order is targeted for.
     * @return IOrder or null if the storage is full
     */
    @Override
    public IOrder getOrder(IStorageReadable storage, IMachineSpecificationReadable machinespecification) {
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

