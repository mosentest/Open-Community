package org.mu.cloudservice.enumtype;

import java.util.HashMap;
import java.util.Map;

public enum NotificationType {

	POST_COMMENT("P_C", "%s commented  [include]%s[/include] on your post [include]%s[/include]"), 
	AT_POST("A_P", "%s mentioned you in post [include]%s[/include]"), 
	AT_COMMENT("A_C", "%s mentioned you in post comment [include]%s[/include]");
	
	private static final Map<String, NotificationType> cache = new HashMap<>();
	
	static {
		for (NotificationType notification : values()) {
			cache.put(notification.ident, notification);
		}
	}
	
	private final String ident;
	
	private final String messageFormat;
	
	private NotificationType(String ident, String messageFormat) {
		this.ident = ident;
		this.messageFormat = messageFormat;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public String getMessage(Object...args) {
		return String.format(messageFormat, args);
	}
	
	public static NotificationType getEnumType(String ident) {
		return cache.get(ident);
	}
	
}
