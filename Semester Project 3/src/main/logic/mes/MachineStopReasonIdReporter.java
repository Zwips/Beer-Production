package logic.mes;

import Acquantiance.IDataChangeCatcher;
import communication.OutCommunication.*;
import org.opcfoundation.ua.builtintypes.DataValue;


public class MachineStopReasonIdReporter implements IDataChangeCatcher {

    private String StopReasonID;
    private AbortEmail am = new AbortEmail();
    private InventoryEmail ie = new InventoryEmail();
    private MaintenenceEmail me = new MaintenenceEmail();
    private PowerLossEmail pe = new PowerLossEmail();
    private StopEmail se = new StopEmail();
    private Machine machine;

    MachineStopReasonIdReporter(Machine machine)
    {
        this.machine = machine;
    }

    @Override
    public void report(DataValue data) {

    switch (StopReasonID){
        case "10":
            ie.SendInventoryEMail();
            break;
        case "11":
            me.SendMaintenenceEMail();
            //
            break;
        case "12":
            se.SendStopEMail();
            //
            break;
        case "13":
            pe.SendPowerLossEmail();
            //
            break;
        case "14":
            am.SendAbortEMail();
            //
            break;

    }
    }
}
