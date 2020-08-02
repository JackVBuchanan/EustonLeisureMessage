package com.elm.messages;

import com.elm.controller.UIController;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Message {

    protected String messageID;
    protected String sender;
    protected String subject;
    protected String body;
    protected String type;

    /**
     * Loop through body to find hashtags and add to arrayList
     */
    protected void addToTrendingList() {

        String[] bodyArray = this.body.split(" ");
        ArrayList<String> trendingList = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < bodyArray.length; i++) {
            if (bodyArray[i].charAt(0) == '#') {
                trendingList.add(j, bodyArray[i]);
                j++;
            }
        }
        saveTrendingList(trendingList);
    }

    /**
     * Write all hashtags from body to file for display on shutdown
     */
    private void saveTrendingList(ArrayList<String> trendingList) {

        String basePath = System.getProperty("user.dir");
        basePath = basePath.split("ELM")[0];

        try {
            FileWriter writer = new FileWriter(basePath + "ELM/src/main/resources/trendingList.txt", true);
            for (int i = 0; i < trendingList.size(); i++) {
                writer.write(System.getProperty("line.separator"));
                writer.write(trendingList.get(i));
            }
            writer.close();
            System.out.println("Write was successful");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    /**
     * Loop through body to find mentions and add to arrayList
     */
    protected void addToMentionsList() {

        String[] bodyArray = this.body.split(" ");
        ArrayList<String> mentionsList = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < bodyArray.length; i++) {
            if (bodyArray[i].charAt(0) == '@') {
                mentionsList.add(j, bodyArray[i]);
                j++;
            }
        }
        saveMentionsList(mentionsList);
    }

    /**
     * Write all mentions from body to file for display on shutdown
     */
    private void saveMentionsList(ArrayList<String> mentionsList) {

        String basePath = System.getProperty("user.dir");
        basePath = basePath.split("ELM")[0];

        try {
            FileWriter writer = new FileWriter(basePath + "ELM/src/main/resources/mentionsList.txt", true);
            for (int i = 0; i < mentionsList.size(); i++) {
                writer.write(System.getProperty("line.separator"));
                writer.write(mentionsList.get(i));
            }
            writer.close();
            System.out.println("Write was successful");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    /**
     * Takes message body and appends full textspeak abbreviations
     */
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

    /**
     * Message body converted to JSON format at written to text
     * file in downloads folder
     */
    public void formatJSON() {

        String bodyJSON = "{\"message\": [" +
                "{\"messageid\": \"" + this.messageID + "\", " +
                "\"sender\": \"" + this.sender + "\", " +
                "\"subject\": \"" + this.subject + "\", " +
                "\"body\": \"" + this.body + "\"}]}";

        String home = System.getProperty("user.home");
        File messageJSON = new File(home + "/Downloads/message.json");
        try {
            FileWriter myWriter = new FileWriter(home + "/Downloads/message.json");
            myWriter.write(bodyJSON);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
