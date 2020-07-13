package com.elm.controller;

import com.elm.messages.Email;
import com.elm.messages.Text;
import com.elm.messages.Tweet;

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
}
