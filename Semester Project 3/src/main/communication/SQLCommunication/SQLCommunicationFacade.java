package communication.SQLCommunication;

import Acquantiance.*;
import communication.ISQLCommunicationFacade;
import communication.SQLCommunication.deleters.RemoveBatchLogByBatchID;
import communication.SQLCommunication.deleters.RemoveMachine;
import communication.SQLCommunication.deleters.RemoveOrdersByOrderID;
import communication.SQLCommunication.inserters.*;
import communication.SQLCommunication.selecters.*;
import communication.SQLCommunication.updaters.OrderStatusSetter;
import communication.SQLCommunication.updaters.OrderUpdater;

import java.sql.Timestamp;
import java.util.*;

/*
Knows everything about the specific database, because communication with a database is not an industry standard

 */

public class SQLCommunicationFacade implements ISQLCommunicationFacade {


    @Override
    public IBatch selectFromBatch(int batchID, String factoryID) {
        BatchRetriever retriever = new BatchRetriever();
        IBatch batch = retriever.getBatch(batchID, factoryID);
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
    public IBatchLog getBatchLogByBatchID(int batchID, String factoryID) {
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
    public List<IProductionOrder> getPendingOrders() {
        return new PendingOrdersRetriever().getPendingOrders();
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
    public void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective, String factoryID) {
        new BatchInserter().insert(batchID,productType,amount,defective,factoryID);

    }

    @Override
    public void InsertIntoBatch_log(int batchID, String MachineID, int orderID, String factoryID) {
        new BatchLogInserter().insert(batchID,MachineID,orderID,factoryID);
    }


    @Override
    public void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product) {
        new DefectiveInserter().insert(machineID,numberOfDefective,productsInBatch,machineSpeed,product);
    }

    @Override
    public void logTemperature(float value, Date timestamp, int batchID, String factoryID) {
        Timestamp time = new Timestamp(timestamp.getTime());
        new TemperatureInserter().insert(batchID,time,value,factoryID);
    }

    @Override
    public void logVibration(float value, Date timestamp, int batchID, String factoryID) {
        Timestamp time = new Timestamp(timestamp.getTime());
        new VibrationInserter().insert(batchID,time,value,factoryID);
    }

    @Override
    public void logHumidity(float value, Date timestamp, int batchID, String factoryID) {
        Timestamp time = new Timestamp(timestamp.getTime());
        new HumidityInserter().insert(batchID,time,value,factoryID);
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

    @Override
    public int getNextOrderID() {
       return new NextOrderIDRetriever().gerNextOrderID();
    }

    @Override
    public int getNextBatchID() {
        return new NextBatchIDRetriever().getNextBatchID();
    }

    @Override
    public void deleteOrderByOrderID(int orderID) {
        new RemoveOrdersByOrderID().delete(orderID);
    }

    @Override
    public void deleteBatchLogByBatchID(int BatchID, String factoryID) {
        new RemoveBatchLogByBatchID().delete(BatchID);
    }

    @Override
    public void deleteMachine(String machineID) {
        new RemoveMachine().delete(machineID);
    }

    @Override
    public HashMap<String, List<IMachineConnectionInformation>> getMachines() {
        return new MachinesRetriever().getMachines();
    }

    @Override
    public List<IMachineConnectionInformation> getMachines(String plantID) {
        return new MachinesRetriever().getMachines(plantID);
    }

    @Override
    public void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password) {
        new MachineInserter().insert(factoryID,machineID,machine_IP,userID,password);
    }

    @Override
    public int getNextBatchID(String plantID) {
        return new NextBatchIDRetrieverByPlant().getNextBatchID(plantID);
    }

    @Override
    public Set<String> getPlantIDs() {
        return new PlantIDRetriever().getPlantIDs();
    }

    @Override
    public void logOrders(List<IProductionOrder> orders) {
        for (IProductionOrder order : orders) {
            this.logOrder(order);
        }
    }

    @Override
    public void updateOrders(List<IProductionOrder> orders) {
        for (IProductionOrder order : orders) {
            this.updateOrder(order);
        }
    }

    @Override
    public void updateOrder(IProductionOrder order){
        int amount = order.getAmount();
        ProductTypeEnum type = order.getProductType();
        int priority = order.getPriority();
        Timestamp earliestDate = new Timestamp(order.getEarliestDeliveryDate().getTime());
        Timestamp latestDate = new Timestamp(order.getLatestDeliveryDate().getTime());
        int orderID = order.getOrderID();
        boolean status = order.getStatus();
        new OrderUpdater().update(amount, type, earliestDate,latestDate,priority,status,orderID);
    }
}
