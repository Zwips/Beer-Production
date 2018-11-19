package systemTest;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import logic.erp.ERP;

import java.util.Date;
import java.util.List;

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
        ERPLevelInitializer.glue();
        erp = new ERP();
    }

    @Given("^all the parameters for an order$")
    public void allTheParametersForAnOrder() throws Throwable {
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
        List<IProductionOrder> orders = erp.getProductionOrders();

        IProductionOrder order;

        boolean correctOrder = false;
        int i = 0;

        do {
            order = erp.getOrder(i);

            if (order.getAmount() == this.amount && order.getProductType() == this.productType
                    && order.getEarliestDeliveryDate() == this.earliestDeliveryDate
                    && order.getLatestDeliveryDate() == this.latestDeliveryDate
                    && order.getPriority() == this.priority) {
                correctOrder = true;
                break;
            }
            i++;
        }while (order != null);

        assertTrue(correctOrder);
    }

    @And("^the order exists in the database$")
    public void theOrderExistsInTheDatabase() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
