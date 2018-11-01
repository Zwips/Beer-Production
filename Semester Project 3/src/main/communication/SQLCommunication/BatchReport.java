//package communication.SQLCommunication;
//
//import Acquantiance.IBatch;
//import Acquantiance.ProductTypeEnum;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//
//public class BatchReport implements IBatch {
//
//    private int batchID;
//    private String machineID;
//    private HashMap<Date, Float> temperatures;
//    private HashMap<Date, Float> vibrations;
//    private HashMap<Date, Float> humidities;
//
//
//    DatabaseConnector dbHandler;
//
//    SelectFromDatabase select = new SelectFromDatabase();
//
//    public BatchReport(){
//
//    }
//
//    private void FillBatchDataIn(int ID){
//        SelectFromDatabase select = new SelectFromDatabase();
//        ResultSet rs = select.SelectFromBatch(ID);
//
//        // this for loop can't be done??
//        /*for (int i: rs){
//
//        }*/
//
//    }
//
//    public HashMap resultSetToHashmap(ResultSet rs) throws SQLException{
//        ResultSetMetaData md = rs.getMetaData();
//        int columns = md.getColumnCount();
//        HashMap<Date, Float> map = new HashMap(columns);
//        while (rs.next()){
//            Date date = new Date(rs.getTimestamp(2).getTime());
//            map.put(date,rs.getFloat(3));
//        }
//
//        return map;
//    }
//
//    private void FillInTemperatures(int batchID)throws SQLException{
//
//        SelectFromDatabase select = new SelectFromDatabase();
//        HashMap map = resultSetToHashmap(select.SelectFromTemperature(batchID));
//        temperatures = map;
//    }
//
//    private void FillInHumidities(int batchID)throws SQLException{
//
//        SelectFromDatabase select = new SelectFromDatabase();
//        HashMap map = resultSetToHashmap(select.SelectFromHumidity(batchID));
//        humidities = map;
//    }
//
//    private void FillInVibrations(int batchID)throws SQLException{
//
//        SelectFromDatabase select = new SelectFromDatabase();
//        HashMap map = resultSetToHashmap(select.SelectFromVibration(batchID));
//        vibrations = map;
//    }
//
//
//    @Override
//    public int getBatchID() {
//        return 0;
//    }
//
//    @Override
//    public ProductTypeEnum getProductType() {
//        return null;
//    }
//
//    @Override
//    public int getTotalProduced() {
//        return 0;
//    }
//
//    @Override
//    public int getTotalDiscarded() {
//        return 0;
//    }
//
//    @Override
//    public HashMap getAmountOfTimeInDifferentStates() {
//        return null;
//    }
//
//    @Override
//    public HashMap getTemperatures() {
//        return null;
//    }
//
//    @Override
//    public HashMap getHumidity() {
//        return null;
//    }
//
//    @Override
//    public HashMap getVibrations() {
//        return null;
//    }
//}
