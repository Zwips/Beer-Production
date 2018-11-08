package logic.mes;

import Acquantiance.IDataChangeCatcher;
import communication.MailCommunication.*;
import org.opcfoundation.ua.builtintypes.DataValue;


public class MachineStopReasonIdReporter implements IDataChangeCatcher {


    AbortEmail am = new AbortEmail();
    InventoryEmail ie = new InventoryEmail();
    MaintenenceEmail me = new MaintenenceEmail();
    PowerLossEmail pe = new PowerLossEmail();
    StopEmail se = new StopEmail();
    private Machine machine;

    public MachineStopReasonIdReporter(Machine machine) {
        this.machine = machine;
    }

    @Override
    public void report(DataValue data) {
    int value = data.getValue().intValue();

    switch (value){
        case 10:
            ie.SendInventoryEMail();
            break;
        case 11:
            me.SendMaintenenceEMail();
            //
            break;
        case 12:
            se.SendStopEMail();
            //
            break;
        case 13:
            pe.SendPowerLossEmail();
            //
            break;
        case 14:
            am.SendAbortEMail();
            //
            break;

    }
    }
}
