package org.mu.cloudservice.entity;

import java.util.Date;

import org.mu.opencomm.common.entity.User;

public class PostComment {

	private long id;
	
	private long post;
	
	private User user;
	
	private String content;
	
	private Date create;
	
	public PostComment() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPost() {
		return post;
	}

	public void setPost(long post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}
	
}
