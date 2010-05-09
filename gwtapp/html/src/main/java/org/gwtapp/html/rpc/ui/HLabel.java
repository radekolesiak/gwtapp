package org.gwtapp.html.rpc.ui;


public class HLabel extends HWidget implements ILeaf, HValue<String> {

	public final static String TAG = "div";

	private String html = "";

	public HLabel() {
		super(TAG);
	}

	public HLabel(String html) {
		this();
		setLeaf(html);
	}

	@Override
	public String getLeaf() {
		return html;
	}

	@Override
	public void setLeaf(String html) {
		this.html = html;
	}

	@Override
	public String getValue() {
		return getLeaf();
	}

	@Override
	public void setValue(String value) {
		setLeaf(value);
	}

}