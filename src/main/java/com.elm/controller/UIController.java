package com.elm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    @FXML
    public TextField messageID;

    @FXML
    private Label label;

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea bodyField;

    @FXML
    private TextField senderField;

    @FXML
    private void submitMessage(ActionEvent event) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        String type = MessageController.getType(messageID.getText());
        String subject = subjectField.getText();
        String body = bodyField.getText();
        String sender = senderField.getText();
        String id = messageID.getText();

        if(type.equals("invalid")){
            label.setText("Invalid message, please try again");
        }else{
            MessageController.handleMessage(type, subject, body, sender, id);
        }

        messageID.setText("");
        subjectField.setText("");
        bodyField.setText("");
        senderField.setText("");

        System.out.println("You clicked me!");
    }

    public void displayError(String errorMsg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
