package com.elm.messages;

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
}
