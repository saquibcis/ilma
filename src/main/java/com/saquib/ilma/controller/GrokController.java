/*
 * package com.saquib.ilma.controller;
 * 
 * import java.util.Map;
 * 
 * import org.springframework.ai.chat.messages.UserMessage; import
 * org.springframework.ai.chat.model.ChatResponse; import
 * org.springframework.ai.chat.prompt.Prompt; import
 * org.springframework.ai.openai.OpenAiChatModel; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import reactor.core.publisher.Flux;
 * 
 * @RestController public class GrokController {
 * 
 * private final OpenAiChatModel chatModel;
 * 
 * public GrokController(OpenAiChatModel chatModel) { this.chatModel =
 * chatModel; }
 * 
 * @GetMapping("/ai/generate") public Map generate(@RequestParam(value =
 * "message", defaultValue = "As salamo alaikum") String message) { return
 * Map.of("generation", this.chatModel.call(message)); }
 * 
 * @GetMapping("/ai/generateStream") public Flux<ChatResponse>
 * generateStream(@RequestParam(value = "message", defaultValue =
 * "As salamo alaikum") String message) { Prompt prompt = new Prompt(new
 * UserMessage(message)); return this.chatModel.stream(prompt); }
 * 
 * 
 * 
 * }
 */