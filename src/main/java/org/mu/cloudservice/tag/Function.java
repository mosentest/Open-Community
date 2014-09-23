package org.mu.cloudservice.tag;

public class Function {

	public static String maskURL(String string) {
		return string.replace(' ', '-');
	}
	
	public static String unmaskURL(String string) {
		if (string == null) {
			return null;
		}
		return string.replace('-', ' ');
	}
	
	public static String sizeInfo(Long size) {
		if (size <= 1000) {
			return size + " B";
		} else if (size <= 1_000_000) {
			return String.format("%.3f KB", (float) size / 1000);
		} else if (size <= 1_000_000_000) {
			return String.format("%.3f MB", (float) size / 1000000);
		}
		return "";
	}
	
}
