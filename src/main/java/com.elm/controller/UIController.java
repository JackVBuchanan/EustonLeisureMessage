package com.elm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    @FXML
    public TextField messageID;

    @FXML
    public Button button;

    @FXML
    public Button shutDownButton;

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
            MessageController.handleMessage(type, sender, subject, body, id);
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

        //Create new pop up to show formatted message
        Stage stage = new Stage();
        VBox box = new VBox();
        stage.setTitle("Euston Leisure");

        Label displayMessageID = new Label();
        Label displaySender = new Label();
        Label displaySubject = new Label();
        Label displayBody = new Label();
        Label displayEmailType = new Label();

        displayMessageID.setText("Message ID: \n" + messageID);
        displaySender.setText("\nSender: \n" + sender);

        if (!subject.equals("")) {
            displaySubject.setText("\nSubject: \n" + subject);
        }

        displayBody.setWrapText(true);
        displayBody.setText("\nBody: \n" + body);

        if (emailType != null) {
            displayEmailType.setText("\nEmail Type: \n" + emailType);
        }
        // Add labels to box
        box.getChildren().add(displayMessageID);
        box.getChildren().add(displaySender);
        box.getChildren().add(displaySubject);
        box.getChildren().add(displayBody);
        box.getChildren().add(displayEmailType);

        Scene scene = new Scene(box, 1250, 350);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void shutdown(ActionEvent event) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        MessageController msgController = new MessageController();

        String incidentReport = msgController.getIncidentReport();
        String mentionsList = msgController.getMentionsList();
        String trendingList = msgController.getTrendingList();

        // Display list read from text file
        this.displayShutdownList(incidentReport, mentionsList, trendingList);
        Stage stage = (Stage) shutDownButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void displayShutdownList(String incidentReport, String mentionsList, String trendingList) {

        Stage stage = new Stage();
        VBox box = new VBox();
        GridPane grid = new GridPane();
        stage.setTitle("Euston Leisure");

        // Set 3 columns for each list
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(30);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        Label firstLabel = new Label("--- Incident Report ---\n");
        Label secondLabel = new Label("--- Mention List ---\n");
        Label thirdLabel = new Label("--- Trending List ---\n");

        Label incidentReportLabel = new Label(incidentReport);
        Label mentionsListLabel = new Label(mentionsList);
        Label trendingListLabel = new Label(trendingList);

        grid.add(firstLabel, 1, 0);
        grid.add(secondLabel, 2, 0);
        grid.add(thirdLabel, 3, 0);

        grid.add(incidentReportLabel, 1, 1);
        grid.add(mentionsListLabel, 2, 1);
        grid.add(trendingListLabel, 3, 1);

        box.getChildren().add(grid);
        Scene scene = new Scene(box, 1250, 1000);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
