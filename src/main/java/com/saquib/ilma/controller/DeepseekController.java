package com.saquib.ilma.controller;

import java.util.Map;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class DeepseekController {

	private final DeepSeekChatModel chatModel;
	
	@Autowired
    public DeepseekController(DeepSeekChatModel chatModel) {
        this.chatModel = chatModel;
    }
	
	@GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "As salamo alaikum") String message) {
		System.out.println("inside the /ai/generate");
        return Map.of("generation", chatModel.call(message));
    }

    @GetMapping("/ai/generateStream")
	public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "As salamo alaikum") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt);
    }
}
