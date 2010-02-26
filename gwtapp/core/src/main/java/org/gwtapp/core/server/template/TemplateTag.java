package org.gwtapp.core.server.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.gwtapp.core.client.template.Template;

@SuppressWarnings("serial")
public class TemplateTag extends TagSupport {

	private String tag;
	private String style;
	private String styleclass;
	private String templating;

	@Override
	public int doStartTag() throws JspException {
		String tag = "div";
		String style = "";
		String styleclass = "";
		if (getTag() != null && !getTag().isEmpty()) {
			tag = getTag();
		}
		if (getStyle() != null && !getStyle().isEmpty()) {
			style = getStyle();
		}
		if (getStyleclass() != null && !getStyleclass().isEmpty()) {
			styleclass = getStyleclass();
		}
		if (!isTemplating()) {
			((HttpServletResponse) pageContext.getResponse()).addHeader(
					Template.Header.TAG, tag);
			((HttpServletResponse) pageContext.getResponse()).addHeader(
					Template.Header.STYLE, style);
			((HttpServletResponse) pageContext.getResponse()).addHeader(
					Template.Header.STYLE_CLASS, styleclass);
			return EVAL_BODY_INCLUDE;
		} else {
			String templating = getTemplating();
			if ("tag".equalsIgnoreCase(templating)) {
				try {
					JspWriter out = pageContext.getOut();
					out.print(StringEscapeUtils.escapeHtml(getTag()));
				} catch (Exception e) {
				}
				return SKIP_BODY;
			} else if ("style".equals(templating)) {
				try {
					JspWriter out = pageContext.getOut();
					out.print(StringEscapeUtils.escapeHtml(getStyle()));
				} catch (Exception e) {
				}
				return SKIP_BODY;
			} else if ("styleclass".equals(templating)) {
				try {
					JspWriter out = pageContext.getOut();
					out.print(StringEscapeUtils.escapeHtml(getStyleclass()));
				} catch (Exception e) {
				}
				return SKIP_BODY;
			} else if ("body".equals(templating)) {
				return EVAL_BODY_INCLUDE;
			} else if ("all".equals(templating)) {
				return EVAL_BODY_INCLUDE;
			} else {
				return EVAL_BODY_INCLUDE;
			}
		}
	}

	private boolean isTemplating() {
		return getTemplating() != null;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		if (tag != null && !tag.isEmpty()) {
			return tag;
		} else {
			return "div";
		}
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		if (style == null) {
			return "";
		} else {
			return style;
		}
	}

	public void setTemplating(String templating) {
		this.templating = templating;
	}

	public String getTemplating() {
		if (templating != null) {
			return templating;
		} else {
			return ((HttpServletRequest) pageContext.getRequest())
					.getParameter("templating");
		}
	}

	public void setStyleclass(String styleclass) {
		this.styleclass = styleclass;
	}

	public String getStyleclass() {
		return styleclass;
	}
}
