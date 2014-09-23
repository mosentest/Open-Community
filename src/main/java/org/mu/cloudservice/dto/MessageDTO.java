package org.mu.cloudservice.dto;

import org.mu.opencomm.coop.entity.Message;

public class MessageDTO implements DataTransferObject<Message> {
	
	private long conversation;
	
	private String content;
	
	private long receiver;
	
	public MessageDTO() {}
	
	public MessageDTO(long conversation, long receiver, String content) {
		this.conversation = conversation;
		this.receiver = receiver;
		this.content = content;
	}

	@Override
	public Message toObject() {
		Message message = new Message();
		message.setConversationId(conversation);
		message.setMessage(content);
		return message;
	}

	@Override
	public MessageDTO toDTO(Message object) {
		return null;
	}

	public long getConversation() {
		return conversation;
	}

	public void setConversation(long conversation) {
		this.conversation = conversation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getReceiver() {
		return receiver;
	}

	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}

}
