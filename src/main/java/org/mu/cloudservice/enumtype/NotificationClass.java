package org.mu.cloudservice.enumtype;

import java.util.HashMap;
import java.util.Map;

public enum NotificationClass {

	SYSTEM("S", "system"), AT("A", "at"), POST("O", "post"), PROJECT("P", "project"), CODE("C", "code");
	
	private static final Map<String, NotificationClass> cache = new HashMap<>();
	
	static {
		for (NotificationClass notification : values()) {
			cache.put(notification.ident, notification);
		}
	}

	private final String ident;
	
	private final String key;
	
	private NotificationClass(String ident, String key) {
		this.ident = ident;
		this.key = key;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public static NotificationClass getEnumType(String ident) {
		return cache.get(ident);
	}
	
	public static NotificationClass getClass(String key) {
		for (NotificationClass notification : values()) {
			if (notification.key.equals(key)) {
				return notification;
			}
		}
		return null;
	}
	
}
