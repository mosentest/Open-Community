package org.mu.cloudservice.tag;

import org.mu.cloudservice.utility.Page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 * 页面的起始为0,页面总数为页面max(pn)+1
* <p>Title: PageTag</p>
* <p>Description: </p>
* <p>Company: ZIMU</p> 
* @author Mu
* @date 2014年9月17日
 */
public class PageTag extends SimpleTagSupport {

	private Page<?> page;
	
	private String url;
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		StringBuilder buffer = new StringBuilder();
		buffer.append("<ul class='pagination'>");
		if (page.getTotalPage() > 5) {
			if (page.getCurrentPage() == 0) {//禁止回到首页的按钮
				buffer.append("<li class='disabled'><a href='javascript:void();'>First</a></li>");
			} else {
				buffer.append("<li><a href='" + url + "'>First</a></li>");
			}
		}
		printPage(buffer);
		if (page.getTotalPage() > 5) {
			if (page.getCurrentPage() + 1 == page.getTotalPage()) {//如果当前页面是最后一页,禁止最后一页的按钮
				buffer.append("<li class='disabled'><a href='javascript:void();'>Last</a></li>");
			} else {
				buffer.append("<li><a href='" + url + "&pn=" + (page.getTotalPage() - 1) + "'>Last</a></li>");
			}
		}
		buffer.append("<li><a href='javascript:void();'>Total " + page.getTotalPage() + " pages</a></li>");
		buffer.append("</ul>");
		out.println(buffer.toString());
	}
	
	private void printPage(StringBuilder buffer) {
		int total = page.getTotalPage() - 1;
		int current = page.getCurrentPage();
		if (total <= 5 || current <= 2) {//在页面数量足够小的情况下,显示1, 2, 3, 4, 5页
			for (int i = 0; i < total + 1 && i < 5; i++) {
				buffer.append(getLink(current, i));
			}
		} else if (total - current < 2) {//如果页面在最后三页内,显示最后5个页面的数字
			for (int i = total - 4, j = 0; j < 5; i++, j++) {
				buffer.append(getLink(current, i));
			}
		} else {
			for (int i = current - 2, j = 0; j < 5; i++, j++) {//其他情况下将当前页面居中
				buffer.append(getLink(current, i));
			}
		}
	}
	
	private String getLink(int current, int index) {
		return String.format("<li %s><a href='%s'>%d</a></li>", 
				current == index ? "class='active'" : "", url + "&pn=" + index, index + 1);
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}