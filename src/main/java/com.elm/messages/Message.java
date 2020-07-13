package com.elm.messages;

import com.elm.controller.UIController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    protected void addToTrendingList(){

        String[] bodyArray = this.body.split(" ");
        ArrayList<String> trendingList = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < bodyArray.length; i++) {
            if (bodyArray[i].charAt(0) == '#') {
                trendingList.add(j,bodyArray[i]);
                j++;
            }
        }
        saveTrendingList(trendingList);
    }

    private void saveTrendingList(ArrayList<String> trendingList){

        String basePath = System.getProperty("user.dir");
        basePath = basePath.split("ELM")[0];

        try {
            FileWriter writer = new FileWriter(basePath + "ELM/src/main/resources/trendingList.txt", true);
            for (int i = 0; i < trendingList.size(); i++) {
                writer.write(System.getProperty( "line.separator" ));
                writer.write(trendingList.get(i));
            }
            writer.close();
            System.out.println("Write was successful");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }


    protected void addToMentionsList(){

        String[] bodyArray = this.body.split(" ");
        ArrayList<String> mentionsList = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < bodyArray.length; i++) {
            if (bodyArray[i].charAt(0) == '@') {
                mentionsList.add(j,bodyArray[i]);
                j++;
            }
        }
        saveMentionsList(mentionsList);
    }

    private void saveMentionsList(ArrayList<String> mentionsList){

        String basePath = System.getProperty("user.dir");
        basePath = basePath.split("ELM")[0];

        try {
            FileWriter writer = new FileWriter(basePath + "ELM/src/main/resources/mentionsList.txt", true);
            for (int i = 0; i < mentionsList.size(); i++) {
                writer.write(System.getProperty( "line.separator" ));
                writer.write(mentionsList.get(i));
            }
            writer.close();
            System.out.println("Write was successful");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
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
