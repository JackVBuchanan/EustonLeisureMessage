package com.elm.messages;

import com.elm.controller.UIController;

public class Message {

    protected String messageID;
    protected String sender;
    protected String subject;
    protected String body;
    protected int subjectLength = 10;
    protected String bodyLength;
    protected String type;


    public void processMessage(String subject, String body){
        System.out.println("Here!");
    }



    public String handleTextspeak(String body){

        return body;
    }

    protected void handleDisplay() {
        UIController ui = new UIController();
        ui.displayMessage(this.messageID, this.sender, this.subject, this.body, null);
    }
}
