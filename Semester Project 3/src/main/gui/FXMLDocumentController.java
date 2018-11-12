/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import static java.lang.Thread.sleep;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import logic.erp.ProductionOrder;

import static javafx.application.Application.launch;


/**
 *
 * @author HCHB
 */
public class FXMLDocumentController implements Initializable  {

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
    private ChoiceBox<String> priorityChoiceBox;

    @FXML
    private Label priorityLabel;

    @FXML
    private Button sendOrderBtn;

    private HashMap<RadioButton, ProductTypeEnum> productToggleMap;

    @FXML
    private TextField earliestDeliveryDateTextField;

    @FXML
    private TextField latestDeliveryDateTextField1;
    @FXML
    private Label orderSucceededLabel;


    @FXML
    private ListView<IProductionOrder> productionOrderListView;

    @FXML
    private Button loadProductionOrdersBtn;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        priorityChoiceBox.getItems().removeAll(priorityChoiceBox.getItems());
        priorityChoiceBox.getItems().addAll("1", "2", "3");
        priorityChoiceBox.getSelectionModel().select("1");



        productToggleMap = new HashMap<>();
        productToggleMap.put(aleRadioBtn,ProductTypeEnum.ALE);
    }
    @FXML
    void DanishHandleBtn(ActionEvent event) {

    }

    @FXML
    void LanguageHandleBtn(ActionEvent event) {

    }

    public boolean parseDate(TextField field){
        if(!field.getText().isEmpty()){
            try{
                Date SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd").parse(field.getText());
                field.setStyle("-fx-border-color: #5aff1e;-fx-border-width: 2;");
                return true;
            } catch (ParseException e) {
            }
        }

        field.setStyle("-fx-border-color: #ff000e;-fx-border-width: 3;");
        return false;
    }

    public boolean testInt(TextField field){
        if(!field.getText().isEmpty()){
            try{
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
        Date earliestDeliveryDate = null;
        Date latestDeliveryDate = null;

        if (!this.testInt(orderAmountTextField)){
            allTrue = false;
        } else {
            amount = Integer.parseInt(orderAmountTextField.getText());
        }

        if(!this.parseDate(earliestDeliveryDateTextField)) {
            allTrue = false;
        } else {
            earliestDeliveryDate = new SimpleDateFormat("yyyy/MM/dd").parse(earliestDeliveryDateTextField.getText());
        }

        if(!this.parseDate(latestDeliveryDateTextField1)) {
            allTrue = false;
        } else {
            latestDeliveryDate = new SimpleDateFormat("yyyy/MM/dd").parse(latestDeliveryDateTextField1.getText());
        }




        ProductTypeEnum selectedType = productToggleMap.get(TypeToggleGroup.getSelectedToggle());


        int priority = Integer.parseInt(priorityChoiceBox.getValue());


        if (allTrue){
            GUIOutFacade.getInstance().addOrder(amount, selectedType, earliestDeliveryDate, latestDeliveryDate, priority );
            orderSucceededLabel.setText("Order sent");

        }

    }



    @FXML
    void addMachineActionHandler(ActionEvent event) {
        String IPAddress = iPaddressTextField.getText() + ":" + portNumberTextField.getText();
        String machineName = machineNameTextField.getText();
        String username = userNameTextField.getText();
        String password = passWordTextField.getText();

        GUIOutFacade.getInstance().addMachine(machineName, IPAddress,username,password);
    }
    @FXML
    void removeMachineActionHandler(ActionEvent event) {
        if(!GUIOutFacade.getInstance().removeMachine(machineNameTextField.getText()))
        {
            machineNameTextField.setStyle("-fx-border-color: #ff000e;-fx-border-width: 3;");
        }
        else
        {
            machineNameTextField.setStyle("-fx-border-color: #00000;-fx-border-width: 3;");
        }
    }


    public void TypeBtnHandleAction(ActionEvent actionEvent) {
    }

    public void MouseClickedActionAction(MouseEvent mouseEvent) {
    }

    @FXML
    void loadProductionOrdersActionHandler(ActionEvent event) {
        productionOrderListView.setItems(FXCollections.observableArrayList(GUIOutFacade.getInstance().getProductionOrderQueue()));


    }

    public void loadOrderInformationActionHandler(ListView.EditEvent<IProductionOrder> iProductionOrderEditEvent) {
    }
}



