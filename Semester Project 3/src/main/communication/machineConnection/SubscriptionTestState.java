package communication.machineConnection;

/** Represents
 *
 */

import Acquantiance.IDataChangeCatcher;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import org.opcfoundation.ua.builtintypes.DataValue;

import java.io.IOException;

class SubscriptionTestState implements IDataChangeCatcher {

    private Test test;

    public SubscriptionTestState(Test test){
        this.test = test;
    }

    @Override
    public void report(DataValue data) {
        try {
            System.out.println(data.getValue());
            if (data.getValue().intValue()==17) {
                test.printProduction();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
