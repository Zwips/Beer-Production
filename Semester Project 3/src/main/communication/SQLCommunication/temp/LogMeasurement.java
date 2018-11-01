package communication.SQLCommunication.temp;

import communication.SQLCommunication.temp.tools.Insert;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogMeasurement {

    static void logMeasurement(int batchID, Date timeOfReading, float value, Connection connection, String tables, String values) {
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, timeOfReading));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.FLOAT, value));

        new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
