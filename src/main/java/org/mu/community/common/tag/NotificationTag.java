package org.mu.community.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.mu.community.common.util.NotificationUtil;
import org.mu.community.coop.entity.Notification;

public class NotificationTag extends SimpleTagSupport {

	private Notification notification;
	
	private static final String NEWLINE_PATTERN = "\\[newline\\]";
	
	private static final String NEWLINE_REPLACEMENT = "<br/>";
	
	private static final String INCLUDE_PATTERN = "(\\[include\\])(.+?)(\\[/include\\])";
	
	private static final String INCLUDE_REPLACEMENT = "<div class='notification-i-include'>$2</div>";
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		StringBuilder buffer = new StringBuilder();
		buffer.append("<div class='notification-i'>");
		buffer.append(NotificationUtil.parse(notification)
				.replaceAll(NEWLINE_PATTERN, NEWLINE_REPLACEMENT)
				.replaceAll(INCLUDE_PATTERN, INCLUDE_REPLACEMENT));
		buffer.append("</div>");
		out.println(buffer.toString());
	}
	
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	
}