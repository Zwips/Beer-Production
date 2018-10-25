package communication.machineConnection;

import communication.machineConnection.status.*;

public interface IStatus {

    BatchAmountCurrent getBatchAmountCurrent(String prefix);
    BatchIDCurrent getBatchIdCurrent(String prefix);
    CurrentState getCurrentState(String prefix);
    Humidity getHumidity(String prefix);
    MachineSpeedCurrent getMachineSpeedCurrent(String prefix);
    MachineSpeedNormalized getMachineSpeedNormalized(String prefix);
    Temperature getTemperature(String prefix);
    Vibration getVibration(String prefix);

}
