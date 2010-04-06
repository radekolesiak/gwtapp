package org.gwtapp.core.rpc.html.ui.core;

public class HTextBox extends HWidget implements IElementValue, HValue<String> {

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
	public String getElementValue() {
		return value;
	}

	@Override
	public void setElementValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return getElementValue();
	}

	@Override
	public void setValue(String value) {
		setElementValue(value);
	}
}
