package com.elm.messages;

import com.elm.controller.UIController;

public class Text extends Message{

    protected short bodyLength = 140;
    protected String messageType = "text";

    public void processText(String type,String sender,String subject,String body, String id){

        this.sender = sender;
        this.subject = subject;
        this.body = body;
        this.type = type;
        this.messageID = id;

        if(!isValid()){
            return;
        }

        this.handleTextSpeak();
        this.addToMentionsList();
        this.addToTrendingList();
        this.handleDisplay();
    }

    private Boolean isValid() {

        UIController error = new UIController();

        //check subject & body length
        if (this.body.length() > this.bodyLength) {
            error.displayError("Your message has an invalid length");
            return false;
        }

        //check phone number is valid
        if (this.sender.length() > 14) {
            error.displayError("Your sender does not have a valid Twitter ID");
            return false;
        }
        return true;
    }

}
