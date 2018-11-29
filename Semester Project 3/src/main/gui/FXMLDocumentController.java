/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
/**
 * Represents all GUI control
 *
 * @author Michael P
 * @param initialize initializes the choicebox & hashmap containing the product types.
 * @param parseDate parses the given text to a date.
 * @param testInt test if integers have been written in textfield, and returns true or false.
 * @param SendOrderHandleActionBtn sends order to the database if all required information is given & returns "order send" if successful.
 * @param addMachineActionHandler adds another machine if present with the required information ipadress, machinename, username & password.
 * @param removeMachineActionHandler removes chosen machine.
 * @param loadProductionOrdersActionHandler load button loads all the pending orders in the database if any & list them in the listview.
 */

import acquantiance.IProductionOrder;
import acquantiance.ProductTypeEnum;

import static java.lang.Thread.sleep;

import java.net.URL;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import static javafx.application.Application.launch;


/**
 *
 * @author HCHB
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label machineNameLabel;

    @FXML
    private Label iPAdressLabel;

    @FXML
    private Label portNumberLabel;

    @FXML
    private TextField machineNameTextField;

    @FXML
    private TextField iPaddressTextField;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label passWordLabel;

    @FXML
    private TextField portNumberTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passWordTextField;

    @FXML
    private RadioButton pilsnerRadioBtn;

    @FXML
    private ToggleGroup TypeToggleGroup;

    @FXML
    private RadioButton wheatRadioBtn;

    @FXML
    private RadioButton stoutRadioBtn;

    @FXML
    private RadioButton aleRadioBtn;

    @FXML
    private RadioButton alchoholFreeRadioBtn;

    @FXML
    private RadioButton iPARadioBtn;

    @FXML
    private Label typeLabel;

    @FXML
    private RadioButton danishRadioButton;

    @FXML
    private ToggleGroup LanguageToggleGroup;

    @FXML
    private RadioButton englishRadioButton;

    @FXML
    private RadioButton germanRadioButton;

    @FXML
    private TextField orderAmountTextField;

    @FXML
    private Label orderAmountLabel;

    @FXML
    private ChoiceBox<Integer> priorityChoiceBox;

    @FXML
    private Label priorityLabel;

    @FXML
    private Button sendOrderBtn;

    private HashMap<RadioButton, ProductTypeEnum> productToggleMap;


    @FXML
    private Label orderSucceededLabel;
    @FXML
    private Button loadProductionOrdersBtn;

    @FXML
    private DatePicker earliestDeliveryDatePicker;
    @FXML
    private DatePicker latestDeliveryDatePicker;

    @FXML
    private ListView<IProductionOrder> productionOrderListView;
    @FXML
    private Button changeOrder;
    private IProductionOrder currentlySelectedOrder;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        priorityChoiceBox.getItems().removeAll(priorityChoiceBox.getItems());
        priorityChoiceBox.getItems().addAll(1, 2, 3);
        priorityChoiceBox.getSelectionModel().select(1);
        productToggleMap = new HashMap<>();
        productToggleMap.put(iPARadioBtn, ProductTypeEnum.IPA);
        productToggleMap.put(aleRadioBtn, ProductTypeEnum.ALE);
        productToggleMap.put(pilsnerRadioBtn, ProductTypeEnum.PILSNER);
        productToggleMap.put(wheatRadioBtn, ProductTypeEnum.WHEAT);
        productToggleMap.put(alchoholFreeRadioBtn, ProductTypeEnum.ALCOHOLFREE);
        productToggleMap.put(stoutRadioBtn, ProductTypeEnum.STOUT);



        ObservableList<IProductionOrder> data = FXCollections.observableArrayList();
        productionOrderListView.setItems(data);

        productionOrderListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IProductionOrder>() {
            @Override
            public void changed(ObservableValue<? extends IProductionOrder> observable, IProductionOrder oldValue, IProductionOrder newValue) {

                currentlySelectedOrder = newValue;
                if(newValue!=null) {
                    loadOrderInformationActionHandler(newValue);
                }
            }
        });

    }

    /**
     * Method for loading information from an IProductionOrder and setting the corresponding fields
     * @param iProductionOrder
     */
    void loadOrderInformationActionHandler(IProductionOrder iProductionOrder) {

        orderAmountTextField.setText(iProductionOrder.getAmount() + "");
        latestDeliveryDatePicker.setValue(iProductionOrder.getLatestDeliveryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        earliestDeliveryDatePicker.setValue(iProductionOrder.getEarliestDeliveryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        priorityChoiceBox.setValue(iProductionOrder.getPriority());

        switch (iProductionOrder.getProductType()) {
            case ALE:
                aleRadioBtn.setSelected(true);
                break;
            case ALCOHOLFREE:
                alchoholFreeRadioBtn.setSelected(true);
                break;
            case IPA:
                iPARadioBtn.setSelected(true);
                break;
            case STOUT:
                stoutRadioBtn.setSelected(true);
                break;
            case WHEAT:
                wheatRadioBtn.setSelected(true);
                break;
            case PILSNER:
                pilsnerRadioBtn.setSelected(true);
                break;

        }
    }


    public boolean parseDate(DatePicker picker) {
        if (picker.getValue() != null) {

            picker.setStyle("-fx-border-color: #5aff1e;-fx-border-width: 2;");
            return true;
        }

        picker.setStyle("-fx-border-color: #ff000e;-fx-border-width: 3;");
        return false;
    }

    public boolean testInt(TextField field) {
        if (!field.getText().isEmpty()) {
            try {
                Integer.parseInt(field.getText());
                field.setStyle("-fx-border-color: #5aff1e;-fx-border-width: 2;");
                return true;
            }catch(NumberFormatException ex){
            }
        }

        field.setStyle("-fx-border-color: #ff000e;-fx-border-width: 3;");
        return false;
    }

    @FXML
    void SendOrderHandleActionBtn(ActionEvent event) throws ParseException {
        boolean allTrue = true;
        int amount = 0;
        Date latestDeliveryDate = null;
        Date earliestDeliveryDate = null;

        if (!this.testInt(orderAmountTextField)) {
            allTrue = false;
        } else {
            amount = Integer.parseInt(orderAmountTextField.getText());
        }

        if (!this.parseDate(earliestDeliveryDatePicker)) {
            allTrue = false;
        } else {
            earliestDeliveryDate = new Date(earliestDeliveryDatePicker.getValue().toEpochDay() * 86400000);
        }

        if (!this.parseDate(latestDeliveryDatePicker)) {

            allTrue = false;
        } else {
            latestDeliveryDate = new Date(latestDeliveryDatePicker.getValue().toEpochDay() * 86400000);
        }

        ProductTypeEnum selectedType = productToggleMap.get(TypeToggleGroup.getSelectedToggle());

        int priority = (priorityChoiceBox.getValue());

        if (allTrue && event.getSource() == sendOrderBtn) {
            GUIOutFacade.getInstance().addOrder(amount, selectedType, earliestDeliveryDate, latestDeliveryDate, priority);
            orderSucceededLabel.setText("Order sent");

        }else if (allTrue&&event.getSource()==changeOrder){
            GUIOutFacade.getInstance().updateOrder(amount,selectedType,earliestDeliveryDate,latestDeliveryDate,priority,currentlySelectedOrder.getOrderID());
        }

    }


    @FXML
    void addMachineActionHandler(ActionEvent event) {
        String IPAddress = iPaddressTextField.getText() + ":" + portNumberTextField.getText();
        String machineName = machineNameTextField.getText();
        String username = userNameTextField.getText();
        String password = passWordTextField.getText();

        GUIOutFacade.getInstance().addMachine(machineName, IPAddress, username, password);
    }

    @FXML
    void removeMachineActionHandler(ActionEvent event) {
        if (!GUIOutFacade.getInstance().removeMachine(machineNameTextField.getText())) {
            machineNameTextField.setStyle("-fx-border-color: #ff000e;-fx-border-width: 3;");
        } else {
            machineNameTextField.setStyle("-fx-border-color: #00000;-fx-border-width: 3;");
        }
    }


    public void TypeBtnHandleAction(ActionEvent actionEvent) {
    }

    public void MouseClickedActionAction(MouseEvent mouseEvent) {

    }

    @FXML
    void loadProductionOrdersActionHandler(ActionEvent event) {
        currentlySelectedOrder = null;
        productionOrderListView.setItems(FXCollections.observableArrayList(GUIOutFacade.getInstance().getProductionOrderQueue()));
    }


}



