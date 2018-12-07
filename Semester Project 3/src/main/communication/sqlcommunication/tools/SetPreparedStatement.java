package communication.sqlcommunication.tools;
/** Represents an prepared statement builder
 * @author Michael P
 * @param setIntoStatement builds the prepared statements with correct data types for the database
 */
import java.io.*;
/** Represents a prepaired statement setter
 * @author Michael P
 * @param setIntoStatement uses a switch & datatype enums
 * to choose the correct statement for inserting date into the database
 * & returns the statement
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

public class SetPreparedStatement {

    public SetPreparedStatement() {
    }

    PreparedStatement setIntoStatement(PreparedStatement statement, List<PrepareInfo> prepareInfos) throws SQLException, IOException {

        for (PrepareInfo prepareInfo : prepareInfos) {
            switch (prepareInfo.getType()){
                case INT:
                    Integer intValue = (Integer) prepareInfo.getData();
                    if (intValue != null){
                        statement.setInt(prepareInfo.getPlace(), intValue);
                    } else {
                        statement.setNull(prepareInfo.getPlace(), Types.INTEGER);
                    }
                    break;
                case FLOAT:
                    Float floatValue = (Float) prepareInfo.getData();
                    statement.setFloat(prepareInfo.getPlace(), floatValue);
                    break;
                case STRING:
                    String stringValue = (String) prepareInfo.getData();
                    statement.setString(prepareInfo.getPlace(), stringValue);
                    break;
                case TIMESTAMP:
                    Timestamp timeValue = (Timestamp) prepareInfo.getData();
                    statement.setTimestamp(prepareInfo.getPlace(), timeValue);
                    break;
                case BOOLEAN:
                    Boolean boolValue = (Boolean)prepareInfo.getData();
                    statement.setBoolean(prepareInfo.getPlace(), boolValue);
                    break;
                case BYTEARRAY:
                    ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
                    ObjectOutputStream objectOS = new ObjectOutputStream(byteArrayOS);
                    objectOS.writeObject(prepareInfo.getData());
                    objectOS.flush();

                    byte[] byteA = byteArrayOS.toByteArray();
                    statement.setBytes(prepareInfo.getPlace(), byteA);
                    break;
                case DOUBLE:
                    Double doubleValue = (Double) prepareInfo.getData();
                    if (doubleValue != null){
                        statement.setDouble(prepareInfo.getPlace(), doubleValue);
                    } else {
                        statement.setNull(prepareInfo.getPlace(), Types.DOUBLE);
                    }
                    break;

            }
        }

        return statement;
    }
}