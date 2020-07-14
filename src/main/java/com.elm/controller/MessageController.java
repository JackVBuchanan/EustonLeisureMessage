package com.elm.controller;

import com.elm.messages.Email;
import com.elm.messages.Text;
import com.elm.messages.Tweet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MessageController {

    public static void handleMessage(String type,String sender,String subject,String body, String id){

        //would have preferred to user
        if(type.equals("email")){
            Email email = new Email();
            email.processEmail(type,sender,subject,body,id);
        }else if(type.equals("tweet")){
            Tweet tweet = new Tweet();
            tweet.processTweet(type,sender,subject,body,id);
        }else if(type.equals("text")){
            Text text = new Text();
            text.processText(type,sender,subject,body,id);
        }
    }

    static String getType(String messageID){

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

    public String getTrendingList(){

        String trendingList = "";
        ArrayList<String> trendingListArray = new ArrayList<>();

        try {
            String basePath = System.getProperty("user.dir");
            basePath = basePath.split("ELM")[0];
            BufferedReader reader = new BufferedReader(new FileReader(basePath + "ELM/src/main/resources/trendingList.txt"));

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] text = line.split(",");
                trendingListArray.add(text[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < trendingListArray.size(); i++) {
            if(!trendingList.contains(trendingListArray.get(i))){
                trendingList = trendingList.concat(trendingListArray.get(i)) + "\n";
            }
        }
        return trendingList;
    }

    public String getIncidentReport(){

        ArrayList<String> incidentReportArray = new ArrayList<>();

        try {
            String basePath = System.getProperty("user.dir");
            basePath = basePath.split("ELM")[0];
            BufferedReader reader = new BufferedReader(new FileReader(basePath + "ELM/src/main/resources/incidentReport.txt"));

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] text = line.split(",");
                incidentReportArray.add(text[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String incidentReport = "";

        for (int i = 0; i < incidentReportArray.size(); i++) {
            incidentReport = incidentReport.concat(incidentReportArray.get(i) + "\n");
        }
        incidentReport = incidentReport.concat("\n");

        return incidentReport;
    }

    public String getMentionsList(){

        String mentionsList = "";

        ArrayList<String> trendingListArray = new ArrayList<>();

        try {
            String basePath = System.getProperty("user.dir");
            basePath = basePath.split("ELM")[0];
            BufferedReader reader = new BufferedReader(new FileReader(basePath + "ELM/src/main/resources/mentionsList.txt"));

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] text = line.split(",");
                trendingListArray.add(text[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < trendingListArray.size(); i++) {
            if(!mentionsList.contains(trendingListArray.get(i))){
                mentionsList = mentionsList.concat(trendingListArray.get(i)) + "\n";
            }
        }
        return mentionsList;
    }
}
