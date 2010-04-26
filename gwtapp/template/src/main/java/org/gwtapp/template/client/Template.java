package org.gwtapp.template.client;

public class Template {

	public static class Header {
		public final static String TAG = "Template-Tag";
		public final static String STYLE_CLASS = "Template-Style-Class";
		public final static String STYLE = "Template-Style";
	}

	private String tag = "div";
	private String style = "";
	private String styleClass = "";
	private String html = "";

	public Template() {
	}

	public Template(String tag, String style,String styleClass, String html) {
		setTag(tag);
		setStyle(style);
		setStyleClass(styleClass);
		setHtml(html);
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		System.out.println("Tag: "+tag);
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

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyleClass() {
		return styleClass;
	}
}
