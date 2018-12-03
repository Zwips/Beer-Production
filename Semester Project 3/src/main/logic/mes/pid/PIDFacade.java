package logic.mes.pid;

import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IOrder;
import logic.mes.mesacquantiance.IPIDFacade;
import logic.mes.mesacquantiance.IStorageReadable;

public class PIDFacade implements IPIDFacade {

    private static SimplePID simplePID;

    private static PIDFacade pidFacade;

    private PIDFacade(){

    }

    public static PIDFacade getInstance(){
        if(pidFacade == null)
        {
            pidFacade = new PIDFacade();
        }

        return pidFacade;
    }

    /**
     * Method for querying the PID for the next order
     * @param storage IStorageReadable for the processing plant
     * @param machinespecification IMachineSpecificationReadable for the machine the order is targeted for.
     * @return IOrder or null if the storage is full
     */
    @Override
    public IOrder getOrder(IStorageReadable storage, IMachineSpecificationReadable machinespecification) {
        return new SimplePID(storage,machinespecification).getIPIDOrder();
    }
}

