package logic.mes.pid;

import logic.mes.IMachineSpecificationReadable;
import logic.mes.IOrder;
import logic.mes.IPIDFacade;
import logic.mes.IStorageReadable;

public class PIDFacade implements IPIDFacade {


    @Override
    public IOrder getOrder(IStorageReadable storage, IMachineSpecificationReadable machinespecification) {
        return new SimplePID(storage,machinespecification).getIPIDOrder();
    }
}

