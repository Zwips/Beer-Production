package logic.mes;

import Acquantiance.IDataChangeCatcher;
import org.opcfoundation.ua.builtintypes.DataValue;


public class MachineStopReasonIdReporter implements IDataChangeCatcher {



    private Machine machine;


    public MachineStopReasonIdReporter(Machine machine) {
        this.machine = machine;
    }

    @Override
    public void report(DataValue data) {
    int value = data.getValue().intValue();

    switch (value){
        case 10:
            MESOutFacade.getInstance().SendInventoryEmail(machine.getMachineID());
            break;
        case 11:
            MESOutFacade.getInstance().SendMaintenenceEmail(machine.getMachineID());
            //
            break;
        case 12:
            MESOutFacade.getInstance().SendStopEmail(machine.getMachineID());
            //
            break;
        case 13:
            MESOutFacade.getInstance().SendPowerlossEmail(machine.getMachineID());
            //
            break;
        case 14:
            MESOutFacade.getInstance().SendAbortEmail(machine.getMachineID());
            //
            break;

    }
    }
}
