package org.mu.cloudservice.entity;

import java.util.Date;
import java.util.List;

import org.mu.opencomm.common.entity.User;

public class MessageConversation {

	private long id;

	private User user;

	private User target;

	private Date create;

	private Date update;

	private boolean read;

	private int nMessage;
	
	private Message recentMessage;

	private List<Message> messages;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public int getnMessage() {
		return nMessage;
	}

	public void setnMessage(int nMessage) {
		this.nMessage = nMessage;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message getRecentMessage() {
		return recentMessage;
	}

	public void setRecentMessage(Message recentMessage) {
		this.recentMessage = recentMessage;
	}

}
