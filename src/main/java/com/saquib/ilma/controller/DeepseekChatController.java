package com.saquib.ilma.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saquib.ilma.dto.ChatMessage;
import com.saquib.ilma.dto.ChatRequest;
import com.saquib.ilma.dto.ChatResponse;

@RestController
public class DeepseekChatController {

	private final ChatClient chatClient;

	public DeepseekChatController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}



	@PostMapping("/chat")
	public ChatResponse chat(@RequestBody ChatRequest request) {
		System.out.println("Request: "+request);
		List<Message> conversationHistory = new ArrayList<>();

		// Convert DTO messages to Spring AI Message types
		for (ChatMessage chatMessage : request.getHistory()) {
			if ("user".equalsIgnoreCase(chatMessage.getRole())) {
				conversationHistory.add(new UserMessage(chatMessage.getContent()));
			} else if ("assistant".equalsIgnoreCase(chatMessage.getRole())) {
				conversationHistory.add(new AssistantMessage(chatMessage.getContent()));
			}
		}
		conversationHistory.add(new UserMessage(request.getPrompt()));

		Prompt prompt = new Prompt(conversationHistory);	
		String response = chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
		System.out.println(response);
		return new ChatResponse(response);
	}

}
