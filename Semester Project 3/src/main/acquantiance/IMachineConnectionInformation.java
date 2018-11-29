package acquantiance;

public interface IMachineConnectionInformation {
    String getMachineID();
    String getMachineIP();
    String getMachineUsername();
    String getMachinePassword();
    IMachineSpecification getMachineSpecification();
}
