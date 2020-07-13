package com.elm.messages;

import com.elm.controller.UIController;

import javax.lang.model.element.Element;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Message {

    protected String messageID;
    protected String sender;
    protected String subject;
    protected String body;
    protected int subjectLength = 10;
    protected String bodyLength;
    protected String type;


    public void processMessage(String type,String sender,String subject,String body, String id){
        System.out.println("Here!");
    }


    protected void handleTextSpeak() {

        String[] body = this.body.split(" ");

        List<String> bodyArray = new ArrayList<String>();
        Collections.addAll(bodyArray, body);

        try {
            String basePath = System.getProperty("user.dir");
            basePath = basePath.split("ELM")[0];
            BufferedReader reader = new BufferedReader(new FileReader(basePath + "ELM/src/main/resources/textwords.csv"));

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] text = line.split(",");

                if (this.body.contains(text[0])) {
                    for (int i = 0; i < bodyArray.size(); i++) {
                        if (bodyArray.get(i).equals(text[0])) {
                            bodyArray.add(i + 1, "<" + text[1] + ">");
                        }
                    }
                }
            }

            String formattedString = "";

            for (int i = 0; i < bodyArray.size(); i++) {
                formattedString = formattedString.concat(bodyArray.get(i) + " ");
            }

            this.body = formattedString.trim();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void handleDisplay() {
        UIController ui = new UIController();
        ui.displayMessage(this.messageID, this.sender, this.subject, this.body, null);
    }
}
