package communication.SQLCommunication.temp;

import Acquantiance.IBatch;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.dataClasses.CommunicationBatch;
import communication.SQLCommunication.temp.tools.Insert;

import java.util.Date;

import static java.lang.Thread.sleep;

public class tester {

    public static void main(String[] args) throws InterruptedException {
        BatchLog log = new BatchLog();
        log.getBatch();


        /*TemperatureInserter inserter = new TemperatureInserter();
        inserter.insert(-1, new Date(), 1.0F);

        sleep(1000);*/

        BatchRetriever retriever = new BatchRetriever();
        IBatch batch = retriever.getBatch(-1);

    }
}
