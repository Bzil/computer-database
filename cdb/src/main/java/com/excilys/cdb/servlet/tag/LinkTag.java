package com.excilys.cdb.servlet.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LinkTag extends SimpleTagSupport {

	private String uri = "";

	private String text = "";

	public void doTag() throws JspException {
		try {
			PageContext pageContext = (PageContext) getJspContext();
			JspWriter out = pageContext.getOut();

			StringWriter sw = new StringWriter();
			sw.append("<a href=\"" + uri + "\" >");

			JspFragment body = getJspBody();
			body.invoke(sw);

			sw.append(text + "</a>");
			out.println(sw.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
