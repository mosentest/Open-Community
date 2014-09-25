package org.mu.community.common.dto;

import org.mu.community.coop.entity.Post;
import org.mu.community.coop.entity.PostComment;

public class PostDTO implements DataTransferObject<Post> {
	
	private String content;
	
	private long post;
	
	public PostDTO() {}

	@Override
	public Post toObject() {
		Post post = new Post();
		post.setContent(content);
		return post;
	}

	public PostComment toComment() {
		PostComment comment = new PostComment();
		comment.setContent(content);
		comment.setPost(post);
		return comment;
	}
	
	@Override
	public PostDTO toDTO(Post object) {
		return null;
	}

	public long getPost() {
		return post;
	}

	public void setPost(long post) {
		this.post = post;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
