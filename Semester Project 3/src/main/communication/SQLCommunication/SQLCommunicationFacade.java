package communication.SQLCommunication;

import Acquantiance.*;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.temp.inserters.*;
import communication.SQLCommunication.temp.selecters.*;
import communication.SQLCommunication.temp.updaters.OrderStatusSetter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
Knows everything about the specific database, because communication with a database is not an industry standard

 */

public class SQLCommunicationFacade implements ISQLCommunicationFacade {


    @Override
    public IBatch selectFromBatch(int batchID) {
        BatchRetriever retriever = new BatchRetriever();
        IBatch batch = retriever.getBatch(batchID);
        return batch;
    }

    @Override
    public Map selectFromTemperature(String machineID, Date dateFrom) {
        Timestamp time = new Timestamp(dateFrom.getTime());
        return new TemperatureByMachineRetriever().getTemperatures(machineID,time);
    }

    @Override
    public Map selectFromHumidity(String machineID, Date dateFrom) {
        Timestamp time = new Timestamp(dateFrom.getTime());
        return new HumidityByMachineRetriever().getHumidity(machineID,time);
    }

    @Override
    public Map selectFromVibration(String machineID, Date dateFrom) {
        Timestamp time = new Timestamp(dateFrom.getTime());
        return new VibrationByMachineRetriever().getVibrations(machineID,time);
    }

    @Override
    public IBatchLog getBatchLogByBatchID(int batchID) {
        return new BatchLogByBatchIDRetriever().getBatchLog(batchID);
    }

    @Override
    public List<IBatchLog> getBatchLogByMachineID(String machineID) {
        return new BatchLogByMachineRetriever().getBatchLogs(machineID);
    }

    @Override
    public IProductionOrder selectFromOrder(int orderID) {
        OrderRetriever retriever = new OrderRetriever();
        IProductionOrder order = retriever.getOrder(orderID);
        return order;
    }

    @Override
    public List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo) {
        PendingOrdersRetriever retriever = new PendingOrdersRetriever();
        Timestamp _dateFrom = new Timestamp(dateFrom.getTime());
        Timestamp _dateTo = new Timestamp(dateTo.getTime());

        return retriever.getPendingOrders(_dateFrom,_dateTo);
    }

    @Override
    public List<IProductionOrder> getCompletedOrders() {
        CompletedOrdersRetriever retriever = new CompletedOrdersRetriever();
        return retriever.getCompletedOrders();
    }

    @Override
    public void setOrderCompleted(int orderId) {
        new OrderStatusSetter().updateStatus(orderId);
    }

    @Override
    public void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective) {
        new BatchInserter().insert(batchID,productType,amount,defective);

    }

    @Override
    public void InsertIntoBatch_log(int batchID, String MachineID, int orderID) {
        new BatchLogInserter().insert(batchID,MachineID,orderID);
    }


    @Override
    public void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product) {
        new DefectiveInserter().insert(machineID,numberOfDefective,productsInBatch,machineSpeed,product);
    }

    @Override
    public void logTemperature(float value, Date timestamp, int batchID) {
        Timestamp time = new Timestamp(timestamp.getTime());
        new TemperatureInserter().insert(batchID,time,value);
    }

    @Override
    public void logVibration(float value, Date timestamp, int batchID) {
        Timestamp time = new Timestamp(timestamp.getTime());
        new VibrationInserter().insert(batchID,time,value);
    }

    @Override
    public void logHumidity(float value, Date timestamp, int batchID) {
        Timestamp time = new Timestamp(timestamp.getTime());
        new HumidityInserter().insert(batchID,time,value);
    }

    @Override
    public void logOrder(IProductionOrder order) {
        int amount = order.getAmount();
        ProductTypeEnum type = order.getProductType();
        int priority = order.getPriority();
        Timestamp earliestDate = new Timestamp(order.getEarliestDeliveryDate().getTime());
        Timestamp latestDate = new Timestamp(order.getLatestDeliveryDate().getTime());
        int orderID = order.getOrderID();
        boolean status = order.getStatus();
        new OrderInserter().insert(amount, type, earliestDate,latestDate,priority,status,orderID);
    }

}
