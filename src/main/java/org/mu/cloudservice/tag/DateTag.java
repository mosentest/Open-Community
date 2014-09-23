package org.mu.cloudservice.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.mu.opencomm.common.util.DateUtil;

public class DateTag extends SimpleTagSupport {
	
	private Date date;
	
	private boolean useFormat;
	
	private String format = "yy-MM-dd HH:mm:ss";
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (useFormat) {
			out.println(DateUtil.getFormattedString(date));
		} else {
			out.println(DateUtil.getTimeString(date, format));
		}
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUseFormat(boolean useFormat) {
		this.useFormat = useFormat;
	}

}