package org.mu.community.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class BreadCrumbTag extends SimpleTagSupport {

	private String library;
	
	private String path;
	
	private String file;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		StringBuilder buffer = new StringBuilder();
		StringBuilder linkPath = new StringBuilder();
		String[] paths = path.split("/");
		buffer.append("<ol class='breadcrumb'>");
		buffer.append("<li><a href='source/library/" + library + ".html'>" + library + "</a></li>");
		for (int i = 0; i < paths.length; i++) {
			String part = paths[i];
			linkPath.append(part);
			linkPath.append("/");
			if (i == paths.length - 1 && file == null) {
				buffer.append("<li class='active'>" + part + "</li></ol>");
			} else {
				buffer.append(String.format("<li><a href='source/library/%s.html?directory=%s'>%s</a></li>", 
						library, linkPath, part));
			}
		}
		if (file != null) {
			buffer.append("<li class='active'>" + file + "</li></ol>");
		}
		out.println(buffer);
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void setLibrary(String library) {
		this.library = library;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
