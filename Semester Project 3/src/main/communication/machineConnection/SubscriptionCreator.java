package communication.machineConnection;

/** Represents the subscription creator.
 * @author Asmus
 * @param createSubscription Method for the creation of a subscription to a client.
 */

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.Subscription;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Attributes;
import org.opcfoundation.ua.core.MonitoringMode;


public class SubscriptionCreator {

    static Subscription createSubscription(UaClient client, NodeId node, IDataChangeCatcher dataChangeCatcher) throws ServiceException, StatusException {
        MonitoredDataItem monitoredDataItem = new MonitoredDataItem(node, Attributes.Value, MonitoringMode.Reporting);
        Subscription subscription = new Subscription();

        MonitoredDataItemListener dataItemListener = new MonitoredDataItemListener() {
            @Override
            public void onDataChange(MonitoredDataItem sender, DataValue prevValue, DataValue value) {
              dataChangeCatcher.report(value);
            }
        };

        client.addSubscription(subscription);
        subscription.addItem(monitoredDataItem);
        monitoredDataItem.setDataChangeListener(dataItemListener);
        return subscription;
    }
}
