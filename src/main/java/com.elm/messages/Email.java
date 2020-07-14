package com.elm.messages;

import com.elm.controller.UIController;

import java.io.FileWriter;
import java.io.IOException;

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

        if(this.emailType.equals("incident")){
            this.handleIncidentReport();
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

    private void handleIncidentReport(){

        var split = this.body.split("\n");
        String incident = "Incident Date: " + this.subject.substring(4) + "\n" + split[0] + "\n" + split[1] + "\n";

        this.addToIncidentReport(incident);
    }

    private void addToIncidentReport(String incident){

        String basePath = System.getProperty("user.dir");
        basePath = basePath.split("ELM")[0];

        try {
            FileWriter writer = new FileWriter(basePath + "ELM/src/main/resources/incidentReport.txt", true);
            writer.write(System.getProperty( "line.separator" ));
            writer.write(incident);
            writer.close();
            System.out.println("Write was successful");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
