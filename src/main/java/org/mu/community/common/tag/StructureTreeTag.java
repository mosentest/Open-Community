package org.mu.community.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.mu.community.code.entity.FileInfo;
import org.mu.community.code.entity.JavaLibrary;
import org.mu.community.common.util.FileUtil;

public class StructureTreeTag extends SimpleTagSupport {

	private List<FileInfo> roots;
	
	private JavaLibrary library;
	
	private String name;
	
	private String ext;
	
	private boolean expand;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		StringBuilder buffer = new StringBuilder();
		buffer.append("<ul class='tree-root tree'>");
		
		parseFileInfo(buffer, roots, "/");
		buffer.append("</ul>");
		out.println(buffer.toString());
	}
	
	private void parseFileInfo(StringBuilder buffer, List<FileInfo> files, String path) {
		buffer.append("<ul class='tree'>");
		if (path.equals(name) || expand) {
			buffer.append("<li class='hidden current'></li>");
		}
		for (FileInfo file : files) {
			if (file.isDir()) {
				if (file.getnFile() == 0) {
					buffer.append("<li class='directory'>");
				} else {
					buffer.append("<li class='directory tree-directory'>");
				}
				buffer.append(file.getName());
				parseFileInfo(buffer, file.getFiles(), file.getName());
				buffer.append("</li>");
			} else {
				String name = FileUtil.getFullName(file.getName(), file.getExtension());
				boolean sameFile = file.getName().equals(this.name) && similarExt(file.getExtension(), this.ext);
				buffer.append("<li class='" + getClassStyle(file.getExtension()) + (sameFile ? " current" : " ") + "'>");
				if (library.hasSource() && !name.contains("$")) {
					buffer.append("<a href='source/library/" + library.getName() + ".html?file=" + 
							(path + name) + "'>");
					buffer.append(name);
					buffer.append("</a>");
				} else {
					buffer.append(name);
				}
				buffer.append("</li>");
			}
		}
		buffer.append("</ul>");
	}
	
	private boolean similarExt(String ext1, String ext2) {
		return ext1.equals(ext2) || ((ext1.equals("class") || ext1.equals("java")) && (ext2.equals("java")));
	}
	
	private String getClassStyle(String extension) {
		switch (extension) {
			case "css": case "gif": case "htm": case "ico": case "jar":
			case "java": case "jpg": case "js": case "ppt": case "rar": case "txt":
			case "xml": 
				return extension;
			case "html" :
				return "htm";
			default:
				return "unknown";
		}
	}

	public void setRoots(List<FileInfo> roots) {
		this.roots = roots;
	}

	public void setLibrary(JavaLibrary library) {
		this.library = library;
	}
	
	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
