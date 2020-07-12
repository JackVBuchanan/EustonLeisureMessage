package com.elm.controller;

import com.elm.messages.Email;
import com.elm.messages.Text;
import com.elm.messages.Tweet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MessageController {

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

        String type = getType(messageID.getText());
        String subject = subjectField.getText();
        String body = bodyField.getText();
        String sender = senderField.getText();
        String id = messageID.getText();

        if(type.equals("invalid")){
            label.setText("Invalid message, please try again");
        }else{
            handleMessage(type, subject, body, sender, id);
        }
        System.out.println("You clicked me!");
    }

    public static void handleMessage(String type, String subject, String body, String sender, String id){

        //would have preferred to user
        if(type.equals("email")){
            Email email = new Email();
            email.processEmail(type,sender,subject,body,id);
        }else if(type.equals("tweet")){
            Tweet tweet = new Tweet();
            tweet.processMessage(subject, body);
        }else if(type.equals("text")){
            Text text = new Text();
            text.processMessage(subject, body);
        }
    }

    private String getType(String messageID){

        char type = messageID.charAt(0);
        String messageType = "Undefined";
        switch(type){
            case 'E':
                messageType = "email";
                break;

            case 'S':
                messageType = "text";
                break;

            case 'T':
                messageType = "tweet";
                break;
            default:
                messageType = "invalid";
        }
        return messageType;
    }
}
