package Acquantiance;

public interface IMESFacade {

    IMesMachine createMachine(String name, String address, String userID, String password);
    int getNextOrderID();
    int getNextBatchID();
}
