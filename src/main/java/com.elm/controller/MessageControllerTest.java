package com.elm.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MessageControllerTest {

    @Test
    void getSMSType() {
        String type = "S";
        String result = MessageController.getType(type);
        Assert.assertEquals(result, "text");
    }

    @Test
    void getTweetType() {
        String type = "T";
        String result = MessageController.getType(type);
        Assert.assertEquals(result, "tweet");
    }

    @Test
    void getEmailType() {
        String type = "E";
        String result = MessageController.getType(type);
        Assert.assertEquals(result, "email");
    }

    @Test
    void getInvalidType() {
        String type = "X";
        String result = MessageController.getType(type);
        Assert.assertEquals(result, "invalid");
    }
}