package logic.mes.mesacquantiance;

import logic.mes.pid.IPIDType;

public interface IPIDFacade {
    IOrder getOrder(IStorageReadable storage ,IMachineSpecificationReadable machinespecification);

    void setPIDType(IPIDType type);
}