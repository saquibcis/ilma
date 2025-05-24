package com.saquib.ilma.dto;

public class ChatResponse {

    private String content;

    public ChatResponse() {}
    public ChatResponse(String content) {
        this.content = content;
    }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
	@Override
	public String toString() {
		return "ChatResponse [content=" + content + "]";
	}
    
    
}
