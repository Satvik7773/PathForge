package com.example.pathforge.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeminiController {
    private final ChatClient chatClient;

    public GeminiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat-client/{message}")
    public String chatPrompt(@PathVariable String message) {
        return chatClient.prompt(message).call().content();
    }
}
