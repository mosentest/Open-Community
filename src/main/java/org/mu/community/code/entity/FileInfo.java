package org.mu.community.code.entity;

import java.util.List;

import org.mu.jmdb.annotations.EmbeddedDocument;
import org.mu.jmdb.annotations.Field;

@EmbeddedDocument
public class FileInfo {

	@Field
	private String name;

	@Field
	private Long size;

	@Field
	private String extension;

	@Field
	private boolean isDir;

	@Field
	private String path;

	@Field
	private int nFile;

	@Field
	private List<FileInfo> files;
	
	public FileInfo() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String type) {
		this.extension = type;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public int getnFile() {
		return nFile;
	}

	public void setnFile(int nFile) {
		this.nFile = nFile;
	}

	public List<FileInfo> getFiles() {
		return files;
	}

	public void setFiles(List<FileInfo> files) {
		this.files = files;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
