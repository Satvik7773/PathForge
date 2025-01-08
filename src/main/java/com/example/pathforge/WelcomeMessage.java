package com.example.pathforge;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {

    public String getWelcomeMessage() {
        return "Finally We are Able to Welcome Somone on this application!";
    }
}
