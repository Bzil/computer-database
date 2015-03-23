package com.excilys.cdb.servlet.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {

	private static final String TEMPLATE = "<li><a href=\"dashboard?page=%s\">%s</a></li>";
	private static final String TEMPLATE_ARROW = "<li><a href=\"dashboard?page=%s\" aria-label=\"%s\"> <span aria-hidden=\"true\">%s</span></a></li>";

	private String page;

	private String count;

	public void doTag() throws JspException, IOException {
		int p = Integer.parseInt(page);
		if(p < 1) {
			p = 1;
		}
		int c = Integer.parseInt(count);
		JspWriter out = getJspContext().getOut();
		StringWriter sw = new StringWriter();
		if (p > 1) {
			sw.append(String.format(TEMPLATE_ARROW, p - 1, "Previous",
					"&laquo;"));
		}
		if ((p - 2) > 0) {
			sw.append(String.format(TEMPLATE, p - 2, p - 2));
		}

		if ((p - 1) > 0) {
			sw.append(String.format(TEMPLATE, p - 1, p - 1));
		}
		
		sw.append(String.format(TEMPLATE, p, p));

		if ((p + 1) <= c) {
			sw.append(String.format(TEMPLATE, p + 1, p + 1));
		}
		if ((p + 2) <= c) {
			sw.append(String.format(TEMPLATE, p + 2, p + 2));
		}
		if (p < c) {
			sw.append(String.format(TEMPLATE_ARROW, p + 1, "Next", "&raquo;"));
		}
		out.println(sw.toString());
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
