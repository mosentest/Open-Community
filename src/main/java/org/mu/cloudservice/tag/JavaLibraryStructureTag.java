package org.mu.cloudservice.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.mu.opencomm.code.entity.FileInfo;
import org.mu.opencomm.code.entity.JavaLibrary;
import org.mu.opencomm.common.util.FileUtil;

public class JavaLibraryStructureTag extends SimpleTagSupport {

	private JavaLibrary javaLibrary;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		StringBuilder buffer = new StringBuilder();
		buffer.append("<div class='library-panel'><div class='library-title'>");
		buffer.append(String.format("<span>File name: <b>%s</b></div><div class='library-content'>", 
				javaLibrary.getName()));
		
		parseFileInfo(buffer, javaLibrary.getStructure(), "/");
		buffer.append("</div></div>");
		out.println(buffer.toString());
	}
	
	private void parseFileInfo(StringBuilder buffer, List<FileInfo> files, String path) {
		buffer.append("<ul class='tree'>");
		for (FileInfo file : files) {
			if (file.isDir()) {
				buffer.append("<li class='directory'>");
				buffer.append("<a href='source/library/" + javaLibrary.getName() + ".html?directory=" + file.getName() + "'>");
				buffer.append(file.getName());
				buffer.append("</a>");
				parseFileInfo(buffer, file.getFiles(), file.getName());
				buffer.append("</li>");
			} else {
				buffer.append("<li class='" + getClassStyle(file.getExtension()) + "'>");
				String name = FileUtil.getFullName(file.getName(), file.getExtension());
				if (javaLibrary.hasSource() && !name.contains("$")) {
					buffer.append("<a href='source/library/" + javaLibrary.getName() + ".html?file=" + 
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

	public void setJavaLibrary(JavaLibrary javaLibrary) {
		this.javaLibrary = javaLibrary;
	}
	
}
