/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineconnection.command;

/** Represents commands.
 * @author HCHB
 *
 */

import communication.machineconnection.ICommand;


public class Command implements ICommand {

    private String identifier="Command.";


    @Override
    public Amount getAmount(String prefix) { return new Amount(prefix + identifier); }

    @Override
    public BatchID getBatchId(String prefix) {
        return new BatchID(prefix + identifier);
    }

    @Override
    public Control getControl(String prefix) {
        return new Control(prefix + identifier);
    }

    @Override
    public MachineSpeed getMachineSpeed(String prefix) {
        return new MachineSpeed(prefix + identifier);
    }

    @Override
    public ProductID getProductId(String prefix) {
        return new ProductID(prefix + identifier);
    }
}
