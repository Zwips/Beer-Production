package logic.mes.pid;

import logic.mes.IMachineSpecificationReadable;
import logic.mes.IOrder;
import logic.mes.IStorageReadable;

public interface PIDType {

    IOrder getIPIDOrder(IStorageReadable storage, IMachineSpecificationReadable machineSpecification);
}
