package org.mu.community.common.constants;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

	private static Properties properties;
	
	private static final String SEPARATOR = ";";
	
	static {
		properties = new Properties();
		InputStream input = null;
		try {
			input = Thread.currentThread().getContextClassLoader().getResourceAsStream("/configs/dev.properties");
			properties.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public static String[] getStringArray(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			return new String[0];
		}
		return value.split(SEPARATOR);
	}

	public static long getLongProperty(String key, long defaultValue) {
		String value = properties.getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return Long.parseLong(value);
	}

    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
	
}
