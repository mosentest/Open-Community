package org.mu.community.coop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mu.community.common.dto.PostDTO;
import org.mu.community.common.entity.User;
import org.mu.community.common.enumtype.NotificationType;
import org.mu.community.common.exception.InfoException;
import org.mu.community.common.service.UserService;
import org.mu.community.common.util.NotificationUtil;
import org.mu.community.coop.entity.Post;
import org.mu.community.coop.entity.PostComment;
import org.mu.community.coop.repository.NotificationRepository;
import org.mu.community.coop.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postService")
public class PostService {

	private PostRepository postRepository;

	private UserService userService;

	private NotificationRepository notificationRepository;
	
	private String pattern = "@([a-zA-Z0-9]+)? ";
	
	public Post getPost(long post, int cPage, int cSize, int lPage, int lSize) {
		Post object = postRepository.getPost(post);
		if (object == null) {
			return null;
		}
		object.setComments(postRepository.getComments(post, cPage * cSize, cSize));
		object.setLikes(postRepository.getLikes(post, lPage, lSize));
		return object;
	}

	@Transactional(readOnly = true)
	public List<Post> getPosts(long user, long auth,
			int pPage, int pSize, int cPage, int cSize, int lPage, int lSize) {
		List<Post> posts = postRepository.getPosts(user, auth, pPage * pSize, pSize);
		for (Post post : posts) {
			post.setComments(postRepository.getComments(post.getId(), cPage * cSize, cSize));
			post.setLikes(postRepository.getLikes(post.getId(), lPage, lSize));
		}
		return posts;
	}
	
	public List<PostComment> getComments(long post, int page, int size) {
		return postRepository.getComments(post, page * size, size);
	}
	
	public List<User> getLikes(long post, int page, int size) {
		return postRepository.getLikes(post, page * size, size);
	}
	
	public void like(long user, long post) {
		postRepository.like(user, post);
	}
	
	public void dislike(long user, long post) {
		postRepository.dislike(user, post);
	}
	
	@Transactional
	public Post createPost(PostDTO dto, long user) throws InfoException {
		Post post = dto.toObject();
		post.setCreate(new Date());
		post.setUser(new User(user));

		Matcher matcher = Pattern.compile(pattern).matcher(post.getContent());
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			String account = matcher.group(1);
			User at = userService.getUser(account);
			if (at == null) {
				continue;
			}
			//add notification
			matcher.appendReplacement(buffer, "[at]a=$1,p=" + at.getPath() + "[/at]");
		}
		matcher.appendTail(buffer);
		post.setContent(buffer.toString());
		
		postRepository.createPost(post);
		if (post.getId() == 0) {
			throw new InfoException("Could not create Post.");
		}
		return postRepository.getPost(post.getId());
	}
	
	@Transactional
	public PostComment createPostComment(PostDTO dto, long user) throws InfoException {
		PostComment comment = dto.toComment();
		Post post = postRepository.getPost(dto.getPost());
		if (post == null) {
			throw new InfoException("Post doesn't exist");
		}
		List<Long> atUser = new ArrayList<>();
		comment.setCreate(new Date());
		comment.setUser(new User(user));

		Matcher matcher = Pattern.compile(pattern).matcher(comment.getContent());
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			String account = matcher.group(1);
			User at = userService.getUser(account);
			if (at == null) {
				continue;
			}
			atUser.add(at.getId());
			matcher.appendReplacement(buffer, "[at]a=$1,p=" + at.getPath() + "[/at]");
		}
		matcher.appendTail(buffer);
		comment.setContent(buffer.toString());
		postRepository.createComment(comment);
		if (comment.getId() == 0) {
			throw new InfoException("Could not create Post Comment.");
		}
		for (Long atId : atUser) {
			notificationRepository.create(NotificationUtil.at(
					atId, NotificationType.AT_COMMENT, user, comment.getId()));
		}
		notificationRepository.create(NotificationUtil.comment(
				post.getUser().getId(), user, comment.getContent(), comment.getPost(), comment.getId()));
		return postRepository.getComment(comment.getId());
	}

    @Autowired
	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

    @Autowired
	public void setNotificationRepository(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}
	
}
