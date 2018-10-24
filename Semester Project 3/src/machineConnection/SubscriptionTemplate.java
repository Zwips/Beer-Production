package machineConnection;


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

class SubscriptionTemplate {

    private UaClient client;
    MonitoredDataItem item;
    MonitoredDataItemListener dataChangeListener;
    Subscription subscription;
    NodeId node;

    public SubscriptionTemplate() {
    }

    public SubscriptionTemplate(UaClient client, MonitoredDataItem item, MonitoredDataItemListener dataChangeListener, Subscription subscription, NodeId node) {
        this.client = client;
        this.item = item;
        this.dataChangeListener = dataChangeListener;
        this.subscription = subscription;
        this.node = node;
        setItem();
    }


    private void setItem() {
        try {
            subscription.addItem(item);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (StatusException e) {
            e.printStackTrace();
        }

        try {
            client.addSubscription(subscription);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (StatusException e) {
            e.printStackTrace();
        }

        item.setDataChangeListener(dataChangeListener);
    }

    private void standardSetup() {
        NodeId node = null; //skal ikke være null
        MonitoredDataItemListener listener = new MonitoredDataItemListener() {
            @Override
            public void onDataChange(MonitoredDataItem monitoredDataItem, DataValue dataValue, DataValue dataValue1) {
                //stuf stuff

                /*reportTo.report(dataValue);

                reportTo.reportTemp(dataValue);*/
            }
        };
        item = new MonitoredDataItem(node, Attributes.Value, MonitoringMode.Reporting);
        item.setDataChangeListener(listener);

        /*client.addSubscription((new Subscription()).addItem(item));

        client.getSubscription(1).removeItem();
        try {
            client.removeSubscription(new Subscription());
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/
    }


}