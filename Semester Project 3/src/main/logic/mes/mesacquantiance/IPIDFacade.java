package logic.mes.mesacquantiance;

public interface IPIDFacade {
    IOrder getOrder(IStorageReadable storage ,IMachineSpecificationReadable machinespecification);
}
