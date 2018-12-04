package logic.mes.mesacquantiance;

import acquantiance.IStorageReadable;
import logic.mes.pid.IPIDType;

public interface IPIDFacade {
    IProductionOrder getOrder(IStorageReadable storage , IMachineSpecificationReadable machinespecification);

    void setPIDType(IPIDType type);
}
