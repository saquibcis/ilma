package com.saquib.ilma.controller;

import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saquib.ilma.dto.ChatResponse;
import com.saquib.ilma.dto.QueryRequest;

import reactor.core.publisher.Mono;

@RestController
public class RagChatController {

	@Value("${spring.ai.deepseek.chat.options.model}")
	private String defaultModel;

	@Value("${spring.ai.deepseek.chat.options.temperature}")
	private double defaultTemperature;

	private final ChatClient chatClient;
	private final VectorStore vectorStore;

	@Autowired
	public RagChatController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
		this.chatClient = chatClientBuilder.build();
		this.vectorStore = vectorStore;
	}
	
	
	@PostMapping("/query")
    public ChatResponse  query(@RequestBody String request) {
		System.out.println("reuest "+request.toString());
        String prompt = request;
        
        SearchRequest search = SearchRequest.builder()
                .query(prompt)
                .topK(3)
                .build();
        var results = vectorStore.similaritySearch(search);
        // Concatenate document contents as context
        String context = results.stream()
                .map(doc -> doc.getText())
                .collect(Collectors.joining("\n"));
        // Send context + question to LLM
        String fullPrompt = "Context:\n" + context + "\n\nQuestion: " + prompt;
        String answer = this.chatClient.prompt()
                .user(fullPrompt)
                .call()
                .content();
        return new ChatResponse(answer);
	}
}
