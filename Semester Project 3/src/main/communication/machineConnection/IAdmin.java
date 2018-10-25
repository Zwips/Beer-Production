package communication.machineConnection;

import communication.machineConnection.admin.*;

public interface IAdmin {

    CurrentProductType getCurrentProductType(String prefix);
    DefectiveProducts getDefectiveProducts(String prefix);
    ProducedProducts getProducedProducts(String prefix);
    StopReasonID getStopReasonId(String prefix);
    StopReasonValue getStopReasonValue(String prefix);
}
