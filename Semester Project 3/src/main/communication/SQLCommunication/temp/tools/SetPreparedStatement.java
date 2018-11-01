package communication.SQLCommunication.temp.tools;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SetPreparedStatement {

    public SetPreparedStatement() {
    }

    PreparedStatement setIntoStatement(PreparedStatement statement, List<PrepareInfo> prepareInfos) throws SQLException {

        for (PrepareInfo prepareInfo : prepareInfos) {
            switch (prepareInfo.getType()){
                case INT:
                    Integer intValue = (Integer) prepareInfo.getData();
                    statement.setInt(prepareInfo.getPlace(), intValue);
                    break;
                case FLOAT:
                    Float floatValue = (Float) prepareInfo.getData();
                    statement.setFloat(prepareInfo.getPlace(), floatValue);
                    break;
                case STRING:
                    String stringValue = (String) prepareInfo.getData();
                    statement.setString(prepareInfo.getPlace(), stringValue);
                    break;
                case DATE:
                    java.util.Date javaDate = (java.util.Date)prepareInfo.getData();
                    Date dateValue = new Date(javaDate.getTime());
                    statement.setDate(prepareInfo.getPlace(), dateValue);
                    break;
            }
        }

        return statement;
    }
}