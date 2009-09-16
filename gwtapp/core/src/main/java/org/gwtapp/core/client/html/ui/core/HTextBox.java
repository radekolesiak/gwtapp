package org.gwtapp.core.client.html.ui.core;

public class HTextBox extends HWidget implements HValue<String> {

	public final static String TAG = "input";

	private String value = "";

	public HTextBox() {
		super(TAG);
	}

	public HTextBox(String value) {
		this();
		setValue(value);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
}
