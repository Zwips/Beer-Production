package Acquantiance;

import org.opcfoundation.ua.builtintypes.DataValue;

public interface IDataChangeCatcher {

    public void report(DataValue newData);


}
