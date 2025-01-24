package com.example.pathforge.service;

import com.example.pathforge.controller.TextToSpeechRequest; 
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class TextToSpeechService {

    public String getTextToSpeechFromJson(TextToSpeechRequest request) {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("E:/credentials.json"));

            TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                    .setCredentialsProvider(() -> credentials)
                    .build();

            try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
                for (TextToSpeechRequest.Segment segment : request.getSegments()) {
                    SynthesisInput input = SynthesisInput.newBuilder().setText(segment.getText()).build();

                    VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                            .setLanguageCode("en-US")
                            .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                            .build();

                    AudioConfig audioConfig = AudioConfig.newBuilder()
                            .setAudioEncoding(AudioEncoding.MP3)
                            .build();

                    SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

                    ByteString audioContents = response.getAudioContent();

                    String outputAudioFile = "E:/output_" + segment.getText().substring(0, 5) + ".mp3";  // You can modify file naming as needed

                    try (OutputStream out = new FileOutputStream(outputAudioFile)) {
                        out.write(audioContents.toByteArray());
                    }
                }

                return "Audio files created successfully!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to generate audio: " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to generate audio: " + e.getMessage();
        }
    }
}
