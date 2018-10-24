/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineConnection.command;

import machineConnection.ICommand;
import machineConnection.MachineConnection;

/**
 *
 * @author HCHB
 */
public class Command implements ICommand {

    private String identifier="Command.";


    @Override
    public Amount getAmount(String prefix) {
        return null;
    }

    @Override
    public BatchID getBatchId() {
        return null;
    }

    @Override
    public Control geControl() {
        return null;
    }

    @Override
    public MachineConnection gMachineConnection() {
        return null;
    }

    @Override
    public MachineSpeed gMachineSpeed() {
        return null;
    }

    @Override
    public ProductID getProductId() {
        return null;
    }
}
