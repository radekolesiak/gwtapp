package org.gwtapp.core.client.html.ui.core;

public class HLabel extends HWidget implements HasHTML {

	public final static String TAG = "div";

	private String html = "";

	public HLabel() {
		super(TAG);
	}

	public HLabel(String html) {
		this();
		setHTML(html);
	}

	@Override
	public String getHTML() {
		return html;
	}

	@Override
	public void setHTML(String html) {
		this.html = html;
	}

}
