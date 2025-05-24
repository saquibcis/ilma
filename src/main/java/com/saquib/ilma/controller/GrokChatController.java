/*
 * package com.saquib.ilma.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.ai.chat.client.ChatClient; import
 * org.springframework.ai.chat.client.ChatClient.ChatClientRequestSpec; import
 * org.springframework.ai.chat.memory.ChatMemory; import
 * org.springframework.ai.chat.messages.UserMessage; import
 * org.springframework.ai.chat.model.ChatResponse; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.saquib.ilma.dto.ChatMessage; import
 * com.saquib.ilma.dto.ChatRequest;
 * 
 * @RestController public class GrokChatController {
 * 
 * private final ChatMemory chatMemory; private final ChatClient chatClient;
 * 
 * public GrokChatController(ChatMemory chatMemory, ChatClient chatClient) {
 * this.chatClient = chatClient; this.chatMemory = chatMemory; }
 * 
 * @PostMapping("/chat") public String chat(@RequestBody ChatRequest userInput)
 * {
 * 
 * ChatClientRequestSpec promptBuilder = this.chatClient.prompt();
 * 
 * List<ChatMessage> history = userInput.getHistory();
 * 
 * if (history != null) { for (ChatMessage msg : history) { if
 * ("assistant".equalsIgnoreCase(msg.getRole())) {
 * promptBuilder.system(msg.getContent()); } else {
 * promptBuilder.user(msg.getContent()); } } }
 * promptBuilder.user(userInput.getPrompt()); String answer =
 * promptBuilder.call().content();
 * 
 * return answer; } }
 */