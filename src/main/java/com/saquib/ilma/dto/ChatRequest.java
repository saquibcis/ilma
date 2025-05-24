package com.saquib.ilma.dto;

import java.util.List;

public class ChatRequest {

    private List<ChatMessage> history;
    private String prompt;

    public ChatRequest() {}
    public ChatRequest(List<ChatMessage> history, String prompt) {
        this.history = history;
        this.prompt = prompt;
    }
    public List<ChatMessage> getHistory() { return history; }
    public void setHistory(List<ChatMessage> history) { this.history = history; }
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
	@Override
	public String toString() {
		return "ChatRequest [history=" + history + ", prompt=" + prompt + "]";
	}
    
    
}

