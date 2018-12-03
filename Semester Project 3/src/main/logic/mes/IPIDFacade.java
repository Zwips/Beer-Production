package logic.mes;

public interface IPIDFacade {
    IOrder getOrder(IStorageReadable storage ,IMachineSpecificationReadable machinespecification);
}
