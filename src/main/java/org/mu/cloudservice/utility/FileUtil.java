package org.mu.cloudservice.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.mu.opencomm.common.constants.PropertiesManager;
import org.mu.opencomm.common.enumtype.FileType;

public class FileUtil {

	public static final String LIBRARY_PATH = PropertiesManager.getProperty("server.file.path.code.java.lib", "F:/file/code/java/lib");

	public static final String LIBRARY_SRC_PATH = PropertiesManager.getProperty("server.file.path.code.java.src", "F:/file/code/java/src");

	public static final String LIBRARY_DOC_PATH = PropertiesManager.getProperty("server.file.path.code.java.doc", "F:/file/code/java/doc");
	
	public static String getFullName(String name, String extension) {
		if (StringUtil.isEmpty(name)) {
			return "." + extension;
		} else if (StringUtil.isEmpty(extension)) {
			return name;
		} else {
			return name + "." + extension;
		}
	}

	
	public static boolean saveLibrary(File file, String alias) throws IOException {
		return copyFile(file, LIBRARY_PATH, alias);
	}
	
	public static boolean saveLibrarySource(File file, String alias) throws IOException {
		return copyFile(file, LIBRARY_SRC_PATH, alias);
	}
	
	public static boolean saveLibraryDoc(File file, String alias) throws IOException {
		return copyFile(file, LIBRARY_DOC_PATH, alias);
	}
	
	public static boolean copyFile(File file, String path, String alias) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		File outputFile = new File(path, alias);
		OutputStream outputStream = new FileOutputStream(outputFile);
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		inputStream.close();
		outputStream.close();
		return true;
	}
	
	public static String getExtension(String file) {
		return file.substring(file.lastIndexOf('.') + 1);
	}
	
	public static boolean isValid(String fileName, String[] extensions) {
		if (fileName.lastIndexOf('.') == -1) {
			return false;
		}
		String ext = getExtension(fileName);
		for (String extension : extensions) {
			if (ext.toLowerCase().equals(extension.trim().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static String getPath(FileType type, String file) {
		return type.getPath() + "/" + file;
	}

	public static String toFile(InputStream inputStream, FileType fileType, String path, String extension) throws IOException {
		try {
			if (fileType == FileType.POST) {
				File file = getPostFile(path, extension);
				OutputStream outputStream = new FileOutputStream(file);
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				outputStream.close();
				return file.getName();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static File getPostFile(String path, String extension) throws IOException {
		return File.createTempFile(path + "-", "." + extension, new File(FileType.POST.getPath()));
	}

	public static byte[] getFileAsStream(String fullPath) {
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(Paths.get(fullPath));
		} catch (Exception e) {
			bytes = new byte[0];
		}
		return bytes;
	}
	
	public static String toDataURI(byte[] bytes, String extension) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("data:image/");
		buffer.append(extension);
		buffer.append(";base64,");
		buffer.append(StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false)));
		return buffer.toString();
	}
	
}
