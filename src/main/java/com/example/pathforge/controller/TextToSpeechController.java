package com.example.pathforge.controller;

import com.example.pathforge.controller.TextToSpeechRequest;
import com.example.pathforge.service.TextToSpeechService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;

    public TextToSpeechController(TextToSpeechService textToSpeechService) {
        this.textToSpeechService = textToSpeechService;
    }

    @PostMapping("/text-to-speech")
    public String textToSpeech(@RequestBody TextToSpeechRequest request) {
        return textToSpeechService.getTextToSpeechFromJson(request);
    }
}
