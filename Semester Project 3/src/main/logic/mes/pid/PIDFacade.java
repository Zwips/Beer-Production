package logic.mes.pid;

import logic.mes.IMachineSpecificationReadable;
import logic.mes.IOrder;
import logic.mes.IPIDFacade;
import logic.mes.IStorageReadable;

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


    @Override
    public IOrder getOrder(IStorageReadable storage, IMachineSpecificationReadable machinespecification) {
        return new SimplePID(storage,machinespecification).getIPIDOrder();
    }
}

