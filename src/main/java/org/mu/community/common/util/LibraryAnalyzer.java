package org.mu.community.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.codec.digest.DigestUtils;
import org.mu.community.code.entity.FileInfo;
import org.mu.community.code.entity.JavaFile;
import org.mu.community.code.entity.JavaLibrary;
import org.mu.pom.entity.Pom;
import org.mu.pom.reader.PomAnalyzer;

public class LibraryAnalyzer {
	
	private static Comparator<FileInfo> lengthComparator = new Comparator<FileInfo>() {
		@Override
		public int compare(FileInfo i1, FileInfo i2) {
			if (i1.getPath().length() > i2.getPath().length()) {
				return -1;
			} else {
				return 1;
			}
		}
	};
	
	private static Comparator<FileInfo> alphaComparator = new Comparator<FileInfo>() {
		@Override
		public int compare(FileInfo i1, FileInfo i2) {
			//compare directories, directories will always be first in a tree
			if (i1.isDir() && i2.isDir()) {
				return i1.getPath().compareTo(i2.getPath());
			}
			if (i1.isDir()) {
				return 1;
			}
			if (i2.isDir()) {
				return -1;
			}
			//compare file names
			return i1.getName().compareTo(i2.getName());
		}
	};

	public static JavaLibrary parseJarFile(String file) throws Exception {
		JavaLibrary javaLibrary = new JavaLibrary();
		long size = 0;
		List<JavaFile> javaFiles = new ArrayList<>();
		List<FileInfo> files = new ArrayList<>();
		List<FileInfo> dirs = new ArrayList<>();
        String fileName = file.substring(file.lastIndexOf("\\") + 1);
        javaLibrary.setName(fileName.substring(0, fileName.lastIndexOf('.')));
        javaLibrary.setExtension(fileName.substring(fileName.lastIndexOf('.') + 1));
        javaLibrary.setVerified(true);
        javaLibrary.setMd5(DigestUtils.md5Hex(new FileInputStream(file)));

		ZipFile zip = new ZipFile(file);
		Enumeration<? extends ZipEntry> entries = zip.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
        	FileInfo info = new FileInfo();
        	info.setPath(entry.getName());
        	info.setSize(entry.getSize());
            if (entry.isDirectory()) {
            	info.setDir(true);
            	info.setnFile(0);
            	dirs.add(info);
            } else {
            	if (!entry.getName().endsWith(".class")) {
        	        javaFiles.add(getJavaFile(zip, entry));
            	}
             	if (entry.getName().equals("pom.xml")) {
            		try {
            			Pom pomInfo = PomAnalyzer.analyzePomFile(zip.getInputStream(entry));
                		javaLibrary.setPomInfo(pomInfo);
            		} catch (Exception e) {}
             	}
            	info.setExtension(entry.getName().substring(entry.getName().lastIndexOf('.') + 1));
            	info.setDir(false);
            	files.add(info);
            }
            size += entry.getSize();
		}
		javaLibrary.setSize(size);
		zip.close();
        Collections.sort(dirs, lengthComparator);
        javaLibrary.setStructure(parseFileInfo(files, dirs));
        int nFile = 0;
        for (FileInfo fileInfo : javaLibrary.getStructure()) {
        	if (fileInfo.isDir()) {
        		nFile += fileInfo.getnFile();
        	} else {
        		nFile++;
        	}
        }
        javaLibrary.setnFile(nFile);
        javaLibrary.setJavaFiles(javaFiles);
        return javaLibrary;
	} 
	
	private static List<FileInfo> parseFileInfo(List<FileInfo> files, List<FileInfo> dirs) {
		List<FileInfo> list = new ArrayList<>();
		for (FileInfo dir : dirs) {
			dir.setName(dir.getPath());
			dir.setFiles(new ArrayList<>());
			list.add(dir);
			
			Iterator<FileInfo> it = files.iterator();
			while (it.hasNext()) {
				FileInfo info = it.next();
				if (info.getPath().startsWith(dir.getPath())) {
					info.setName(info.getPath().substring(info.getPath().lastIndexOf('/') + 1));

			    	String fullName = info.getPath().substring(info.getPath().lastIndexOf('/') + 1);
			    	String[] nameExt = getNameExt(fullName);
			    	info.setName(nameExt[0]);
			    	info.setExtension(nameExt[1]);
					dir.getFiles().add(info);
					dir.setnFile(dir.getnFile() + 1);
					it.remove();
				}
			}
		}
		//build library object, all contents will be tree structured
		Iterator<FileInfo> it = list.iterator();
		while (it.hasNext()) {
			FileInfo dir = it.next();
			for (FileInfo directory : dirs) {
				if (!dir.getPath().equals(directory.getPath()) && 
						dir.getPath().startsWith(directory.getPath())) {
					if (directory.getFiles() == null) {
						directory.setFiles(new ArrayList<>());
					}
					directory.getFiles().add(dir);
					directory.setnFile(directory.getnFile() + 1);
					it.remove();
					break;
				}
			}
		}
		for (FileInfo file : list) {
			if (file.isDir()) {
				
			}
		}
		setDirectoryInfo(list);
		sortDirectoriesAlphabetically(list);
		return list;
	}
	
	//set size and files information for directories recursively
	private static void setDirectoryInfo(List<FileInfo> directory) {
		for (FileInfo file : directory) {
			if (file.isDir()) {
				file.setSize(getTotalSize(file));
				file.setnFile(getTotalFiles(file));
				setDirectoryInfo(file.getFiles());
			}
		}
	}
	
	private static void sortDirectoriesAlphabetically(List<FileInfo> dir) {
		Collections.sort(dir, alphaComparator);
		for (FileInfo file : dir) {
			if (file.isDir()) {
				sortDirectoriesAlphabetically(file.getFiles());
			}
		}
	}
	
	public static List<JavaFile> parseSourceFile(String file, String library, boolean javaOnly) throws Exception {
		List<JavaFile> files = new ArrayList<>();
		ZipFile zip = new ZipFile(file);
		Enumeration<? extends ZipEntry> entries = zip.entries();
		while (entries.hasMoreElements()) {
		    ZipEntry entry = entries.nextElement();
	    	if (entry.isDirectory()) {
	    		continue;
	    	}
	    	if (javaOnly && !entry.getName().endsWith(".java")) {
	    		continue;
	    	}
	        files.add(getJavaFile(zip, entry));
		}
		zip.close();
	    return files;
	}
	
	private static JavaFile getJavaFile(ZipFile zip, ZipEntry entry) throws Exception {
		JavaFile java = new JavaFile();
    	java.setSize(entry.getSize());
    	String fullName = entry.getName().substring(entry.getName().lastIndexOf('/') + 1, entry.getName().length());
    	if (entry.getName().indexOf('/') != -1) {
	    	java.setPath(entry.getName().substring(0, entry.getName().lastIndexOf('/')));
    	} else {
    		java.setPath("");
    	}
    	String[] nameExt = getNameExt(fullName);
    	java.setName(nameExt[0]);
    	java.setExtension(nameExt[1]);
		BufferedReader reader = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));  
		String line;
		StringBuilder buffer = new StringBuilder();
		int lines = 0;
		while((line = reader.readLine()) != null){  
			lines++;
			buffer.append(line);
			if (line.trim().length() > 0) {
				buffer.append("\n");
			}
		}
		reader.close();
		if (isMarkUp(java.getExtension())) {
			java.setContent(buffer.toString().replace("<", "&lt;").replace(">", "&gt;"));
		} else {
			java.setContent(buffer.toString());
		}
		java.setnLine(lines);
    	java.setTags(new String[] { "java", java.getName()});
    	return java;
	}
	
	private static String[] getNameExt(String fullName) {
    	if (fullName.startsWith(".")) {//for hidden files : .svn, .temp
	    	return new String[] { "", fullName.substring(1, fullName.length()) };
    	} else if (!fullName.contains(".")) {// for binary files : METAINF, BIN 
	    	return new String[] { fullName, ""};
    	} else {//for normal files
	    	return new String[] { 
	    			fullName.substring(0, fullName.lastIndexOf('.')), 
	    			fullName.substring(fullName.lastIndexOf('.') + 1) };
    	}
	}
	
	private static boolean isMarkUp(String extension) {
		return extension.equals("xml") || extension.equals("html") || extension.equals("html") || extension.equals("tld");
	}
	
	public static int getTotalFiles(FileInfo directory) {
		int files = 0;
		if (directory.getnFile() > 0) {
			for (FileInfo fileInfo : directory.getFiles()) {
				if (fileInfo.isDir()) {
					files += getTotalFiles(fileInfo);
				} else {
					files++;
				}
			}
		}
		return files;
	}
	
	public static long getTotalSize(FileInfo directory) {
		long size = 0;
		if (directory.getnFile() > 0) {
			for (FileInfo fileInfo : directory.getFiles()) {
				if (fileInfo.isDir()) {
					size += getTotalSize(fileInfo);
				} else {
					size += fileInfo.getSize();
				}
			}
		}
		return size;
	}
	
}
