Feature:Pseudo unit testing - The system can manipulate orders in the database

  Background: SQL module
    Given a connection to the SQL module, orders
    Given a connection to the database,orders

#  IProductionOrder selectFromOrder(int orderID);
#  void logOrder(IProductionOrder order);
  Scenario: Selecting a order from database
    Given that an order with ID -1 exists
    When retrieving an order with ID -1
    Then the correct order is retrieved

#  List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo);
#  void logOrder(IProductionOrder order);
  Scenario: Selecting pending orders from database
    Given that a pending order with ID -1 exists
    When retrieving a pending orders
    Then a pending order with ID -1 is retrieved

#  List<IProductionOrder> getCompletedOrders();
#  void logOrder(IProductionOrder order);
  Scenario: Selecting completed orders from database
    Given that a completed order with ID -1 exists
    When retrieving completed orders
    Then the completed orders is retrieved

#  void setOrderCompleted(int orderId);
#  void logOrder(IProductionOrder order);
#  IProductionOrder selectFromOrder(int orderID);
   Scenario: Setting an order as completed
     Given that an uncompleted order with ID -1 exists
     When setting the order with ID -1 to completed
     Then the order with ID -1 is set to completed