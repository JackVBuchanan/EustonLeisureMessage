package com.elm.controller;

import com.elm.messages.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    @FXML
    public TextField messageID;

    @FXML
    public Button button;

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

        if (type.equals("invalid")) {
            label.setText("Invalid message, please try again");
        } else {
            MessageController.handleMessage(type, subject, body, sender, id);
        }

        messageID.setText("");
        subjectField.setText("");
        bodyField.setText("");
        senderField.setText("");

        System.out.println("You clicked me!");
    }

    public void displayError(String errorMsg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }

    @FXML
    public void displayMessage(String messageID, String sender, String subject, String body, String emailType) {

        Stage stage = new Stage();
        VBox box = new VBox();

        Label displayMessageID = new Label();
        Label displaySender = new Label();
        Label displaySubject = new Label();
        Label displayBody = new Label();
        Label displayEmailType = new Label();

        displayMessageID.setText("Message ID: \n" + messageID);
        displaySender.setText("\nSender: \n" + sender);
        displaySubject.setText("\nSubject: \n" + subject);
        displayBody.setText("\nBody: \n" + body);
        if (emailType != null){
            displayEmailType.setText("\nEmail Type: \n" + emailType);
        }

        box.getChildren().add(displayMessageID);
        box.getChildren().add(displaySender);
        box.getChildren().add(displaySubject);
        box.getChildren().add(displayBody);
        box.getChildren().add(displayEmailType);

        Scene scene = new Scene(box, 550, 450);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
