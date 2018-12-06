package logic.mes.mesacquantiance;

import acquantiance.IMachine;

public interface IMesMachine extends IMachine {

    boolean executeOrder(IProductionOrder order, float batchId);


    IMachineSpecificationReadable getMachineSpecificationReadable();

    ISpeedOptimizable getMachineSpecificationOptimizable();
}
