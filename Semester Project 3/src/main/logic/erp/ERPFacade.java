package logic.erp;

import acquantiance.IERPFacade;
import acquantiance.IBusinessOrder;
import acquantiance.ProductTypeEnum;
import acquantiance.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ERPFacade implements IERPFacade {

    ERP erp;

    public ERPFacade() {
        this.erp = new ERP();
    }

    @Override
    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        return erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
    }

    @Override
    public boolean addMachine(String processingPlantID, String machineName, String IPAddress, String userID, String password) {
        return erp.addMachine(processingPlantID, machineName, IPAddress, userID, password);
    }

    @Override
    public boolean addMachine(String machineName, String IPAddress, String userID, String password) {
        return erp.addMachine(machineName, IPAddress, userID, password);
    }

    @Override
    public void addProcessingPlant(String plantID) {
        erp.addProcessingPlant(plantID);
    }

    @Override
    public void removeProcessingPlant(String plantID) {
        erp.removeProcessingPlant(plantID);
    }

    @Override
    public boolean removeMachine(String processingPantID, String machineName) {
        return erp.removeMachine(processingPantID, machineName);
    }

    @Override
    public boolean removeMachine(String machineName) {
        return erp.removeMachine(machineName);
    }

    @Override
    public List<IBusinessOrder> getProductionOrderQueue() {
      return this.erp.getProductionOrders();
    }

    @Override
    public boolean updateOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID) {
      return erp.changeOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority, orderID);
    }

    @Override
    public IBusinessOrder getOrder(int orderID) {
        return this.erp.getOrder(orderID);
    }

    @Override
    public IOEE getOEEByMachine(String machineID, String factoryID) {
        return ERPOutFacade.getInstance().getOEEByMachine(machineID,factoryID);
    }

    @Override
    public int getNextOrderID() {
        return this.erp.getNextOrderID();
    }

    public Set<String> getProcessingPlants(){
        return  erp.getProcessingPlants();
    }
    @Override
    public Set<String> getMachineIDsByFactoryID(String factoryID) {
        return ERPOutFacade.getInstance().getMachineIDsByFactoryID(factoryID);
    }
    public IOEEToGUI getOEE(String machineID, String factoryID) {
        return ERPOutFacade.getInstance().getOEE(machineID,factoryID);
    }


}
