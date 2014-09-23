package org.mu.cloudservice.utility;

import java.util.Date;

import org.mu.opencomm.common.entity.User;
import org.mu.opencomm.common.enumtype.NotificationClass;
import org.mu.opencomm.common.enumtype.NotificationType;
import org.mu.opencomm.common.repository.UserRepository;
import org.mu.opencomm.coop.entity.Notification;
import org.mu.opencomm.coop.entity.Post;
import org.mu.opencomm.coop.entity.PostComment;
import org.mu.opencomm.coop.repository.PostRepository;
import org.mu.opencomm.coop.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;

public class NotificationUtil {

	private static PostRepository postRepository = (PostRepository) ApplicationContextProvider.getApplicationContext().getBean("postRepository");

	private static UserRepository userRepository = (UserRepository) ApplicationContextProvider.getApplicationContext().getBean("userRepository");
	
	private static ProjectRepository projectRepository = (ProjectRepository) ApplicationContextProvider.getApplicationContext().getBean("projectRepository");
	
	public static Notification comment(long user, long from, String content, long post, long comment) {
		Notification notification = new Notification();
		notification.setUser(new User(user));
		notification.setClassType(NotificationClass.POST);
		notification.setCreate(new Date());
		notification.setContent(content);
		notification.setType(NotificationType.POST_COMMENT);
		notification.setLinkId1(post);
		notification.setLinkId2(comment);
		notification.setLinkUser(new User(from));
		return notification;
	}
	
	public static Notification at(long user, NotificationType type, long from, long linkId) {
		Notification notification = new Notification();
		notification.setUser(new User(user));
		notification.setClassType(NotificationClass.AT);
		notification.setType(type);
		notification.setCreate(new Date());
		notification.setLinkUser(new User(from));
		notification.setLinkId1(linkId);
		return notification;
	}
	
	public static String parse(Notification notification) {
		switch (notification.getClassType()) {
			case SYSTEM:
				return parseSystem(notification);
			case PROJECT:
				return parseProject(notification);
			case POST:
				return parsePost(notification);
			case AT:
				return parseAt(notification);
			case CODE:
				return parseCode(notification);
			default:
				return null;
		}
	}
	
	private static String parseSystem(Notification notification) {
		return null;
	}
	
	private static String parsePost(Notification notification) {
		switch (notification.getType()) {
			case POST_COMMENT:
				Post post = postRepository.getPost(notification.getLinkId1());
				PostComment comment = postRepository.getComment(notification.getLinkId2());
				return NotificationType.POST_COMMENT.getMessage(
						TagUtil.buildUserLink(notification.getLinkUser()),
						TagUtil.buildCommentLink(notification.getLinkUser(), comment),
						TagUtil.buildPostLink(notification.getUser(), post));
			default :
				return null;
		}
	}
	
	@Transactional
	private static String parseAt(Notification notification) {
		switch (notification.getType()) {
			case AT_POST:
				Post post = postRepository.getPost(notification.getLinkId1());
				return NotificationType.AT_POST.getMessage(
						TagUtil.buildUserLink(notification.getLinkUser()),
						TagUtil.buildPostLink(notification.getUser(), post));
			case AT_COMMENT:
				PostComment comment = postRepository.getComment(notification.getLinkId1());
				return NotificationType.AT_COMMENT.getMessage(
						TagUtil.buildUserLink(notification.getLinkUser()),
						TagUtil.buildCommentLink(notification.getUser(), comment));
			default :
				return null;
		}
	}
	
	private static String parseProject(Notification notification) {
		return null;
	}
	
	private static String parseCode(Notification notification) {
		return null;
	}
	
}
