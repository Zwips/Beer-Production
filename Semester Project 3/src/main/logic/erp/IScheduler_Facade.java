package logic.erp;

import Acquantiance.IProductionOrder;

import java.util.List;

public interface IScheduler_Facade {

    List<IProductionOrder> schedule(List<IProductionOrder> orders);
}
