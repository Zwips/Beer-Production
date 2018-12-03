package logic.mes.pid;

import logic.mes.mesacquantiance.IMachineSpecificationReadable;
import logic.mes.mesacquantiance.IOrder;
import logic.mes.mesacquantiance.IStorageReadable;

public interface PIDType {

    IOrder getIPIDOrder(IStorageReadable storage, IMachineSpecificationReadable machineSpecification);
}
