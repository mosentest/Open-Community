package org.mu.community.common.enumtype;

import org.mu.community.common.constants.PropertiesManager;

public enum FileType {

	IMAGE("i", "server.file.path.image", "F:/file/image"), 
	POST("o", "server.file.path.image.post", "F:/file/image/post"),
	PROFILE("u", "server.file.path.image.profile", "F:/file/image/profile"), 
	PROJECT("p", "server.file.path.image.project", "F:/file/image/project");
	
	private final String path;
	
	private final String key;
	
	private FileType(String key, String propertyKey, String defaultValue) {
		path = PropertiesManager.getProperty(propertyKey, defaultValue);
		this.key = key;
	}
	
	public String getPath() {
		return path;
	}
	
	public static FileType getType(String key) {
		for (FileType type : values()) {
			if (type.key.equals(key)) {
				return type;
			}
		}
		return null;
	}
	
}
