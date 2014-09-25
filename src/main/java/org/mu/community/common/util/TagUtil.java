package org.mu.community.common.util;

import org.mu.community.common.entity.User;
import org.mu.community.common.tag.PostContentParser;
import org.mu.community.coop.entity.Post;
import org.mu.community.coop.entity.PostComment;

public class TagUtil {

	public static String buildUserLink(User user) {
		return String.format("<a href='coop/u/%s' class='user-link'><img class='profile-medium' src='image/u/%s'/><span>%s</span></a>", 
				user.getPath(), user.getProfile(), user.getAccount());
	}
	
	public static String buildPostLink(User user, Post post) {
		return String.format("<a href='coop/u/%s/o/%d'>%s</a>", user.getPath(), post.getId(), 
				PostContentParser.getTextOnly(post.getContent(), 100));
	}
	
	public static String buildCommentLink(User user, PostComment comment) {
		return String.format("<a href='coop/u/%s/pc/%d'>%s</a>", user.getPath(), comment.getId(), 
				PostContentParser.parse(comment.getContent()));
	}
	
}
