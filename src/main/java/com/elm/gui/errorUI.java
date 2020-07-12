package com.elm.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class errorUI{

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



    public void displayError(String errorMsg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorMsg);
        alert.showAndWait();
        clearFrom();
    }

    private void clearFrom() {




    }

}
