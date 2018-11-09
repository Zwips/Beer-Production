package systemTest;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;
import logic.erp.ProductionOrder;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MakeOrder {
    private ERP erp;
    private int amount;
    private ProductTypeEnum productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;


    @Given("^the system is initialized$")
    public void theSystemIsInitialized() throws Throwable {
        erp = new ERP();

    }

    @Given("^all the parameters$")
    public void allTheParameters() throws Throwable {
        this.amount = 100;
        this.productType = ProductTypeEnum.ALCOHOLFREE;
        this.earliestDeliveryDate = new Date(0);
        this.latestDeliveryDate = new Date();
        this.priority = 1;
    }

    @When("^adding the order to the queue$")
    public void addingTheOrderToTheQueue() throws Throwable {
        this.erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);

    }

    @Then("^the order exists in the queue$")
    public void theOrderExistsInTheQueue() throws Throwable {
        Queue<IProductionOrder> pQueue = erp.getProductionOrderQueue();
        boolean correctOrder = false;
        while (!pQueue.isEmpty()) {
            IProductionOrder pO = pQueue.poll();

            if (pO.getAmount() == this.amount && pO.getProductType() == this.productType && pO.getEarliestDeliveryDate() == this.earliestDeliveryDate && pO.getLatestDeliveryDate() == this.latestDeliveryDate && pO.getPriority() == this.priority) {
                correctOrder = true;
                break;
            }
        }
        assertTrue(correctOrder);
    }
}
