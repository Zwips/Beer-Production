package machineConnection;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;
import machineConnection.admin.*;

public interface IAdmin {

    CurrentProductType getCurrentProductType(String prefix);
    DefectiveProducts getDefectiveProducts(String prefix);
    ProducedProducts getProducedProducts(String prefix);
    StopReasonID getStopReasonId(String prefix);
    StopReasonValue getStopReasonValue(String prefix);
}
