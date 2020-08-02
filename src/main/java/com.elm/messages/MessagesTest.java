package com.elm.messages;

import com.elm.controller.MessageController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MessagesTest {

    @Test
    void processEmail() {

        Email email = new Email();

        email.sender = "Hello@world.com";
        email.subject = "Lorem ipsum dolor";
        email.body = "Lorem ipsum dolor Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium. Totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est. Qui dolorem ipsum quia dolor sit amet https://www.bbc.co.uk/news, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem https://www.google.com/ ullam corporis suscipit laboriosam.";
        email.type = "email";
        email.messageID = "E123456789";

        email.processEmail(email.type,email.sender,email.subject,email.body, email.messageID);
    }

    @Test
    void processText() {

        Text text = new Text();

        text.sender = "+447911123456";
        text.subject = "";
        text.body = "Voluptatem ipsum dolor Sed B4 perspiciatis unde #omnis BRB natus erro sit voluptatem accusantium IMNSHO laudantium. Nemo fyeo laudantium";
        text.type = "text";
        text.messageID = "S123456789";

        text.processText(text.type,text.sender,text.subject,text.body, text.messageID);
    }

    @Test
    void processTweet() {

        Tweet tweet = new Tweet();

        tweet.sender = "@HelloWorld";
        tweet.subject = "";
        tweet.body = "@JonJones ipsum dolor Sed B4 perspiciatis unde #omnis BRB natus erro sit voluptatem accusantium IMNSHO #laudantium Nemo fyeo #laudantium";
        tweet.type = "tweet";
        tweet.messageID = "T123456789";

        tweet.processTweet(tweet.type,tweet.sender,tweet.subject,tweet.body, tweet.messageID);
    }


}