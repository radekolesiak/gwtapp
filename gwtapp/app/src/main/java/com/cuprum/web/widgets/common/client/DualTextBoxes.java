package com.cuprum.web.widgets.common.client;

import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TDualValue;
import com.cuprum.web.common.client.exceptions.DualTextFieldInvalidException;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.Validator;

public abstract class DualTextBoxes {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1580316239913266196L;

	protected abstract TextBox createTextBox();

	private final TextBox textA = createTextBox();

	private final TextBox textB = createTextBox();

	public DualTextBoxes() {

	}

	public TDualStringValue getValues() {
		TDualStringValue value = new TDualStringValue();
		value.value = getTextA().getValue();
		value.second = getTextB().getValue();
		return value;
	}

	public String getValue() throws DualTextFieldInvalidException {
		if (getTextA().getValue().equals(getTextB().getValue())) {
			return getTextA().getValue();
		} else {
			throw new DualTextFieldInvalidException();
		}
	}

	public boolean isValid() {
		try {
			getValue();
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}

	public void clear() {
		getTextA().setValue("");
		getTextB().setValue("");
	}

	public void setStyleName(String styleName) {
		getTextA().setStyleName(styleName);
		getTextB().setStyleName(styleName);
	}

	/**
	 * @return the textA
	 */
	public TextBox getTextA() {
		return textA;
	}

	/**
	 * @return the textB
	 */
	public TextBox getTextB() {
		return textB;
	}

	public void attachTo(LayoutContainer container) {
		container.add(getTextA());
		container.add(getTextB());
	}

	public void setFieldLabelA(String label) {
		getTextA().setFieldLabel(label);
	}

	public void setFieldLabelB(String label) {
		getTextB().setFieldLabel(label);
	}

	public void setFieldLabel(String labelA, String labelB) {
		setFieldLabelA(labelA);
		setFieldLabelB(labelB);
	}

	public void setEmptyTextA(String emptyText) {
		getTextA().setEmptyText(emptyText);
	}

	public void setEmptyTextB(String emptyText) {
		getTextB().setEmptyText(emptyText);
	}

	public void setEmptyText(String emptyText) {
		setEmptyTextA(emptyText);
		setEmptyTextB(emptyText);
	}

	public void setEmptyText(String emptyTextA, String emptyTextB) {
		setEmptyTextA(emptyTextA);
		setEmptyTextB(emptyTextB);
	}

	@SuppressWarnings("unchecked")
	public void setValidatorA(Validator validator) {
		getTextA().setValidator(validator);
	}

	@SuppressWarnings("unchecked")
	public void setValidatorB(Validator validator) {
		getTextB().setValidator(validator);
	}

	public void setValidator(Validator<?, ?> validator) {
		setValidatorA(validator);
		setValidatorB(validator);
	}

	@SuppressWarnings("unchecked")
	public void setValidator(Validator validatorA, Validator validatorB) {
		setValidatorA(validatorA);
		setValidatorB(validatorB);
	}

	public boolean validate() {
		boolean validate = true;
		validate &= getTextA().validate();
		validate &= getTextB().validate();
		return validate;
	}

	public void setAllowBlank(boolean allow) {
		getTextA().setAllowBlank(allow);
		getTextB().setAllowBlank(allow);
	}

	public void setValue(String value) {
		getTextA().setValue(value);
		getTextB().setValue(value);
	}

	public void setValue(TDualValue<String> value) {
		getTextA().setValue(value.value);
		getTextB().setValue(value.second);
	}
}