package org.gwtapp.core.server.template;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.gwtapp.core.client.template.Template;

@SuppressWarnings("serial")
public class TemplateTag extends TagSupport {

	private String tag;
	private String style;

	@Override
	public int doStartTag() throws JspException {
		String tag = "div";
		String style = "";
		if (getTag() != null && !getTag().isEmpty()) {
			tag = getTag();
		}
		if (getStyle() != null && !getStyle().isEmpty()) {
			style = getStyle();
		}
		((HttpServletResponse) pageContext.getResponse()).addHeader(
				Template.Header.TAG, tag);
		((HttpServletResponse) pageContext.getResponse()).addHeader(
				Template.Header.STYLE, style);
		return EVAL_PAGE;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
}
