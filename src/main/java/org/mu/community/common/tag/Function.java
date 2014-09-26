package org.mu.community.common.tag;

import org.mu.community.common.enumtype.FileType;
import org.mu.community.common.util.DateUtil;
import org.mu.community.common.util.FileUtil;

import java.util.Date;

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
	
	public static String profile(String path) {
		String fullPath = FileUtil.getPath(FileType.PROFILE, path);
		return FileUtil.toDataURI(FileUtil.getFileAsStream(fullPath), 
				path.substring(path.lastIndexOf('.') + 1));
	}

    public static String toDate(Date date) {
        return DateUtil.getFormattedString(date);
    }

    public static String toMonth(Date date) {
        return DateUtil.parseMonth(date);
    }

}
