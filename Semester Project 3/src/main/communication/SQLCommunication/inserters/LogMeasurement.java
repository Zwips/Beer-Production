package communication.SQLCommunication.inserters;
/** Represents a logger
 * @author Michael P
 * @param logMeasurement method creates an ArrayList containing the measurements containing batchID, timestamp, value & inserts it into the database.
 */
import communication.SQLCommunication.tools.Insert;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogMeasurement {

    static void logMeasurement(int batchID, Date timeOfReading, float value, Connection connection, String tables, String values, String factoryID) {
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, timeOfReading));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.FLOAT, value));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.STRING, factoryID));

        new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
