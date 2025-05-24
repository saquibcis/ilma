package com.saquib.ilma.dto;

public class ChatMessage {

	private String role;
    private String content;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ChatMessage(String role, String content) {
		super();
		this.role = role;
		this.content = content;
	}
	public ChatMessage() {
		super();
		
	}
	@Override
	public String toString() {
		return "ChatMessage [role=" + role + ", content=" + content + "]";
	}
    
    
}
