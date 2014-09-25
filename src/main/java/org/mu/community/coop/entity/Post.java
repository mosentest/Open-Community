package org.mu.community.coop.entity;

import java.util.Date;
import java.util.List;

import org.mu.community.common.entity.User;

public class Post {

	private long id;
	
	private User user;
	
	private Date create;
	
	private String content;
	
	private int nLike;
	
	private int nComment;
	
	private int nShare;
	
	private List<User> likes;

	private boolean liked;
	
	private List<PostComment> comments;
	
	public Post() {}

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

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public int getnLike() {
		return nLike;
	}

	public void setnLike(int nLike) {
		this.nLike = nLike;
	}

	public int getnComment() {
		return nComment;
	}

	public void setnComment(int nComment) {
		this.nComment = nComment;
	}

	public int getnShare() {
		return nShare;
	}

	public void setnShare(int nShare) {
		this.nShare = nShare;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	public List<PostComment> getComments() {
		return comments;
	}

	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

}
