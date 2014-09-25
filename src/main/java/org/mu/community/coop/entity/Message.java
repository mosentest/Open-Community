package org.mu.community.coop.entity;

import java.util.Date;

public class Message {

	private long id;
	
	private long conversationId;
	
	private String message;
	
	private Date create;
	
	private boolean outward;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getConversationId() {
		return conversationId;
	}

	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public boolean isOutward() {
		return outward;
	}

	public void setOutward(boolean outward) {
		this.outward = outward;
	}
	
}
