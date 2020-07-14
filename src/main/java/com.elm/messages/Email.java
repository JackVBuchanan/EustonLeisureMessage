package com.elm.messages;

import com.elm.controller.UIController;

public class Email extends Message{

    protected String emailType;

    public void processEmail(String type,String sender,String subject,String body, String id) {

        this.sender = sender;
        this.subject = subject;
        this.body = body;
        this.type = type;
        this.messageID = id;

        if(subject.startsWith("SIR")){
            this.emailType = "incident";
        }else{
            this.emailType = "standard";
        }

        if(!isValid()){
            return;
        }

        this.filterURL();
        this.handleDisplay();
    }

    @Override
    protected void handleDisplay() {
        UIController ui = new UIController();
        ui.displayMessage(this.messageID, this.sender, this.subject, this.body, this.emailType);
    }


    private void filterURL() {
        String[] bodyArray = this.body.split(" ");

        for(int i = 0; i < bodyArray.length; i++){

            if (bodyArray[i].contains("http")){
                bodyArray[i] = "<URL Quarantined>";
            }
        }
        this.body = String.join(" ", bodyArray);
    }



    private boolean isValid() {

        //check subject & body length
        if (this.subject.length() > 20 || this.body.length() > 1028) {
            UIController error = new UIController();
            error.displayError("Your message has an invalid length");
            return false;
        }
        //TODO check for valid email and messageID
        return true;
    }
}
