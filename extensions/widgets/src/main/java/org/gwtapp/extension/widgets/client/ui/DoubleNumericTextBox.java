package org.gwtapp.extension.widgets.client.ui;

public class DoubleNumericTextBox extends NumericTextBox<Double> {

	public static class Style {
		public final static String DOUBLE_NUMERIC_TEXT_BOX = "doubleNumericTextBox";
	}

	public DoubleNumericTextBox(Double minValue, Double maxValue) {
		super(minValue, maxValue);
		addStyleName(Style.DOUBLE_NUMERIC_TEXT_BOX);
	}

	public DoubleNumericTextBox() {
		addStyleName(Style.DOUBLE_NUMERIC_TEXT_BOX);
	}

	@Override
	protected Double getInitMax() {
		return Double.MAX_VALUE;
	}

	@Override
	protected Double getInitMin() {
		return -Double.MAX_VALUE;
	}

	@Override
	protected Double parse(String t) throws NumberFormatException {
		return Double.parseDouble(t);
	}

}
