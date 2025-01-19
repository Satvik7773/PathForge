package com.example.pathforge.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeminiController {
    private final ChatClient chatClient;

    public GeminiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat-client")
    public String chatPrompt(@RequestBody String message) {

        String masterPrompt = "Generate a script: Create a script based on the project_description, ensuring the length aligns with the duration. Use a standard narration speed (e.g., 3 words per second).\n" +
                "                Split the script: Divide the script into smaller parts based on logical breaks (e.g., introduction, main content, conclusion). Allocate a proportion of the total duration to each segment.\n" +
                "        Extract keywords: For each segment, extract an array of relevant keywords.\n" +
                "                Output JSON: Return the script along with an array of segments containing:\n" +
                "        The text for each segment.\n" +
                "        Duration allocated to each segment.\n" +
                "                Keywords for the segment.\n" +
                "\n" +
                "        Approach\n" +
                "        Calculate Word Limit:\n" +
                "        Determine the total word count for the script using the formula:\n" +
                "        max_words = duration * words_per_second.\n" +
                "                Generate Script:\n" +
                "        Create a coherent script based on the project_description within the calculated word limit.\n" +
                "                Ensure the script aligns with the theme.\n" +
                "        Split the Script:\n" +
                "        Divide the script into logical segments (e.g., introduction, main content, conclusion).\n" +
                "        Assign a proportionate duration to each segment based on its content.\n" +
                "                Extract Keywords:\n" +
                "        Use keyword extraction techniques to identify relevant terms for each segment.\n" +
                "        Return JSON:\n" +
                "        Include the full script and an array of segments with text, duration, and keywords.\n"
                ;

        return chatClient.prompt(message
          + masterPrompt
        ).call().content();
    }
}
