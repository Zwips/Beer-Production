package communication.machineconnection;

import communication.machineconnection.admin.*;

public interface IAdmin {

    CurrentProductType getCurrentProductType(String prefix);
    DefectiveProducts getDefectiveProducts(String prefix);
    ProducedProducts getProducedProducts(String prefix);
    StopReasonID getStopReasonId(String prefix);
    StopReasonValue getStopReasonValue(String prefix);
}
