package org.mu.community.coop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mu.community.common.entity.User;
import org.mu.community.coop.entity.Post;
import org.mu.community.coop.entity.PostComment;
import org.springframework.stereotype.Repository;

@Repository("postRepository")
public interface PostRepository {
	
	public Post getPost(long post);
	
	public PostComment getComment(long comment);
	
	public List<Post> getWall(@Param("user") long user, @Param("auth") long auth,
			@Param("offset") int offset, @Param("size") int size);

	public List<Post> getPosts(@Param("user") long user, @Param("auth") long auth,
			@Param("offset") int offset, @Param("size") int size);
	
	public List<User> getLikes(@Param("post") long post, @Param("offset") int offset, @Param("size") int size);

	public List<PostComment> getComments(@Param("post") long post, @Param("offset") int offset, @Param("size") int size);

	public void like(@Param("user") long user, @Param("post") long post);

	public void dislike(@Param("user") long user, @Param("post") long post);
	
	public int createPost(Post post);
	
	public int createComment(PostComment comment);
	
}
