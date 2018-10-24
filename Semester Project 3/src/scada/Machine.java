package scada;


import Acquantiance.IMachineConnection;
import machineConnection.MachineConnection;

public class Machine {

    private String name;
    private String address;
    private String userID;
    private String password;
    private IMachineConnection iMachineConnection;

    public Machine(String name, String address, String userID, String password){
        this.name = name;
        this.address = address;
        this.userID = userID;
        this.password = password;
        connectToMachine();
    }

    private void connectToMachine(){
        iMachineConnection = new MachineConnection( address, userID, password);
    };

    //skal flyttes til persistens
    /*private removeMachine(String address){

    }*/

}
