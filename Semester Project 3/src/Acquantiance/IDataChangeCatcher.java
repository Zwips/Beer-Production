package Acquantiance;

import org.opcfoundation.ua.builtintypes.DataValue;

public interface IDataChangeCatcher {

    public void temperatureChange(DataValue data);
    public void humidityChange(DataValue data);
    public void vibrationChange(DataValue data);
    public void machineStateChange(DataValue data);
    public void machineStopReasonID(DataValue data);


}
