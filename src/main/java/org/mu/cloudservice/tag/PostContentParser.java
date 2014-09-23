package org.mu.cloudservice.tag;

public class PostContentParser {
	
	public static final String IMAGE_PATTERN = "(\\[img\\])(.+?)(\\[/img\\])";
	
	public static final String IMAGE_REPLACEMENT = "<a href='image/o/$2' target='_blank'><img class='post-image' src='image/o/$2'></a>";
	
	public static final String URL_PATTERN = "(\\[url\\])(.+?)(\\[/url\\])";
	
	public static final String URL_REPLACEMENT = "<a href='$2'>$2</a>";
	
	public static final String AT_PATTERN = "(\\[at\\])a=(.+?),p=(.+?)(\\[/at\\])";
	
	public static final String AT_REPLACEMENT = "<a href='coop/u/$3'>@$2</a>";

	public static String parse(String content) {
		return content
				.replaceAll(IMAGE_PATTERN, IMAGE_REPLACEMENT)
				.replaceAll(URL_PATTERN, URL_REPLACEMENT)
				.replaceAll(AT_PATTERN, AT_REPLACEMENT);
	}

	public static String getTextOnly(String content, int maxLength) {
		content = content
				.replaceAll(IMAGE_PATTERN, "")
				.replaceAll(URL_PATTERN, URL_REPLACEMENT)
				.replaceAll(AT_PATTERN, AT_REPLACEMENT);
		if (maxLength <= content.length()) {
			
		}
		return content;
	}
	
}
