/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package gui;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.SessionActivationException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.UserIdentity;
import com.prosysopc.ua.client.AddressSpaceException;
import com.prosysopc.ua.client.ServerConnectionException;
import com.prosysopc.ua.client.UaClient;
import static java.lang.Thread.sleep;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import opcSamples.SampleConsoleClient;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.core.ApplicationDescription;
import org.opcfoundation.ua.core.ApplicationType;
import org.opcfoundation.ua.core.Identifiers;

import static javafx.application.Application.launch;
import static org.opcfoundation.ua.core.Identifiers.DataValue;
import static org.opcfoundation.ua.core.Identifiers.StatusCode;
import org.opcfoundation.ua.core.TimestampsToReturn;
import org.opcfoundation.ua.transport.security.SecurityMode;

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


//    DanishGUI danishGUI;



    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        priorityChoiceBox.getItems().removeAll(priorityChoiceBox.getItems());
        priorityChoiceBox.getItems().addAll("1", "2", "3");
        priorityChoiceBox.getSelectionModel().select("1");
    }
        @FXML
        void DanishHandleBtn(ActionEvent event) {

        }

        @FXML
        void LanguageHandleBtn(ActionEvent event) {

        }

        @FXML
        void SendOrderHandleActionBtn(ActionEvent event) {
        int amount = Integer.parseInt(orderAmountTextField.getText());
        Toggle productType = TypeToggleGroup.getSelectedToggle();
           // addOrder(int amount, float productType, Date earliestDeliveryDate, java.util.Date latestDeliveryDate, int priority)
       // GUIOutFacade.getInstance().addOrder(amount,)
         //   amount, productType, earliestDeliveryDate, latestDeliveryDate, priority)
        }


    @FXML
    void addMachineActionHandler(ActionEvent event) {
        String address = iPaddressTextField.getText() + ":" + portNumberTextField.getText();
        String machineName = machineNameTextField.getText();
        String username = userNameTextField.getText();
        String password = passWordTextField.getText();

        GUIOutFacade.getInstance().addMachine(machineName,address,username,password);
    }

    @FXML
    void TypeBtnHandleAction(ActionEvent event) {

    }

    }



