package org.gwtapp.core.client.template;

public class Template {

	private String tag = "div";
	private String style = "";
	private String html = "";

	public Template() {
	}

	public Template(String tag, String style, String html) {
		setTag(tag);
		setStyle(style);
		setHtml(html);
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

	public void setHtml(String html) {
		this.html = html;
	}

	public String getHtml() {
		return html;
	}
}
