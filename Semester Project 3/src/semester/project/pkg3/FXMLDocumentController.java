/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package semester.project.pkg3;

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
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import static org.opcfoundation.ua.core.Identifiers.DataValue;
import static org.opcfoundation.ua.core.Identifiers.StatusCode;
import org.opcfoundation.ua.core.TimestampsToReturn;
import org.opcfoundation.ua.transport.security.SecurityMode;

/**
 *
 * @author HCHB
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Label label;
    private UaClient client;


    @FXML
    private RadioButton danishRadioButton;

    @FXML
    private ToggleGroup languageToggleGroup;

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
    private RadioButton iPARadioBtn;

    @FXML
    private ToggleGroup typeToggleGroup;

    @FXML
    private Button sendOrderBtn;

    @FXML
    private RadioButton pilsnerRadioBtn;

    @FXML
    private RadioButton wheatRadioBtn;

    @FXML
    private RadioButton stoutRadioBtn;

    @FXML
    private RadioButton aleRadioBtn;

    @FXML
    private RadioButton alchoholFreeRadioBtn;

//    DanishGUI danishGUI;


    @FXML



    void TypeBtnHandleAction(ActionEvent event) {


    }
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        priorityChoiceBox.getItems().removeAll(priorityChoiceBox.getItems());
        priorityChoiceBox.getItems().addAll("1","2","3");
        priorityChoiceBox.getSelectionModel().select("1");

    }
    @FXML
    void DanishHandleAction(ActionEvent event) {

    
    
}}
