package logic.mes.pid;

import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IProductionOrder;
import acquantiance.IStorageReadable;

public interface PIDType {

    IProductionOrder getIPIDOrder(IStorageReadable storage, IMachineSpecificationReadable machineSpecification);
}
