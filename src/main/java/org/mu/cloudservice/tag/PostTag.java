package org.mu.cloudservice.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.mu.opencomm.common.entity.User;
import org.mu.opencomm.common.util.DateUtil;
import org.mu.opencomm.coop.entity.Post;
import org.mu.opencomm.coop.entity.PostComment;

public class PostTag extends SimpleTagSupport {
	
	private Post post;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		long id = post.getId();
		StringBuilder buffer = new StringBuilder();
		buffer.append("<div class='post' id='post-" + id + "'>");
		buffer.append("<div class='post-profile'>");
		buffer.append("<a href='coop/u/" + post.getUser().getPath() + "'>");
		buffer.append("<img class='profile-default' src='image/u/" + post.getUser().getProfile() + "'></a>");
		buffer.append("<div>" + post.getUser().getAccount() + "</div></div>");
		buffer.append("<div class='post-body'>");
		buffer.append(" <div class='post-content'> " + PostContentParser.parse(post.getContent()) + "</div>");

		buffer.append("<div class='post-info'>");
		buffer.append("<span class='aui-icon aui-icon-small aui-iconfont-time'></span>");
		buffer.append(" <span> " + DateUtil.getTimeString(post.getCreate(), null) + "</span>");
		buffer.append("<span class='tab'></span>");
		buffer.append("<span class='aui-icon aui-icon-small aui-iconfont-comment' title='comments'></span>");
		buffer.append(" <span id='n-comment-" + id + "'> " + post.getnComment() + "</span>");
		buffer.append(" <span class='tab'></span>");
		buffer.append("<a href='javascript:" + (post.isLiked() ? "dislike" : "like") + "(" + id + ")' title='Likes'");
		buffer.append("	class='aui-icon aui-icon-small aui-iconfont-like like-link " + (post.isLiked() ? "positive" : "negative") + "'></a>");
		buffer.append(" <span id='n-like-" + id + "'> " + post.getnLike() + "</span>");
		buffer.append("<span class='tab'></span> </div>");
		if (post.getnLike() >= 0) {
			buffer.append("<div class='post-like'>");
			for (User like : post.getLikes()) {
				buffer.append("<a href='coop/u/" + like.getPath() + "' title='" + like.getAccount() + 
						"'><img class='profile-small' src='image/u/" + like.getProfile() + "'></a>");
			}
			if (post.getnLike() >= post.getLikes().size()) {
				buffer.append("<a href='javascript:showLikes(" + id + ")' class='heavy' id='show-like-" + id + "' title='See More'>...</a>");
			}
			buffer.append("</div>");
		}
		buffer.append("<div class='border-top'></div>");
		buffer.append("<div class='post-comment-l'>");
		if (post.getnComment() > 0) {
			buffer.append("<div class='post-comment-l'>");
			for (int i = 0; i < post.getComments().size(); i++) {
				PostComment comment = post.getComments().get(i);
				buffer.append("<div class='post-comment-i " + (i != 0 ? "border-top" : "") + "'>");
				buffer.append("<div class='post-comment-profile'>");
				buffer.append("<a href='coop/u/" + comment.getUser().getPath() + 
						"'><img class='profile-medium' src='image/u/" + comment.getUser().getProfile() + "'/></a></div>");
				buffer.append("<div class='post-comment-content'>");
				buffer.append("<span>" + PostContentParser.parse(comment.getContent()) + "</span><br/>");
				buffer.append("<span class='post-comment-info'>");
				buffer.append("<a href='coop/u/" + comment.getUser().getPath() + "'>" + comment.getUser().getAccount() + "</a>");
				buffer.append(" <span class='tab'></span>");
				buffer.append("<a href=\"javascript:replyComment(" + id + ",'" + comment.getUser().getAccount() + "')\" title='Reply'");
				buffer.append(" class='aui-icon aui-icon-small aui-iconfont-quote'></a> ");
				buffer.append(" <span> " + DateUtil.getTimeString(comment.getCreate(), null) + 
						"</span></span></div><div class='clearfix'></div></div>");
			}
			if (post.getnComment() > post.getComments().size()) {
				buffer.append("<div class='text-center' style='padding: 3px 0px;'>");
				buffer.append("<a href='javascript:showComments(" + id + ")' id='show-comment-" + id + "'>Show More</a></div>");
			}
			buffer.append("</div>");
		}
		buffer.append("</div>");
		buffer.append(" <div class='comment-form'>");
		buffer.append("<input type='text' class='text long-field' id='comment-" + id + "' placeholder='Comment here...'/>");
		buffer.append("<a href='javascript:comment(" + id + ")'>Comment</a>");
		buffer.append("</div></div><div class='clearfix'></div></div>");
		out.println(buffer.toString());
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}