package org.gwtapp.core.server.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.gwtapp.core.client.template.Template;

@SuppressWarnings("serial")
public class TemplateTag extends TagSupport {

	private String tag;
	private String style;
	private Boolean dictionary;

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
		if (!isDictionary()) {
			((HttpServletResponse) pageContext.getResponse()).addHeader(
					Template.Header.TAG, tag);
			((HttpServletResponse) pageContext.getResponse()).addHeader(
					Template.Header.STYLE, style);
		} else {
			try {
				JspWriter out = pageContext.getOut();
			} catch (Exception e) {
				return SKIP_PAGE;
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
		} catch (Exception e) {
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
	}

	private boolean isDictionary() {
		if (getDictionary() != null) {
			return getDictionary();
		}
		String dictionary = ((HttpServletRequest) pageContext.getRequest())
				.getParameter("dictionary");
		return "true".equalsIgnoreCase(dictionary);
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

	public void setDictionary(Boolean dictionary) {
		this.dictionary = dictionary;
	}

	public Boolean getDictionary() {
		return dictionary;
	}
}
