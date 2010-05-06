package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class WrapperWidget extends Widget implements HasHTML {

	public WrapperWidget(String id) {
		this(DOM.getElementById(id));
	}

	public WrapperWidget(Element e) {
		setElement(e);
	}

	@Override
	public String getHTML() {
		return getElement().getInnerHTML();
	}

	@Override
	public void setHTML(String value) {
		getElement().setInnerHTML(value);
	}

	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	@Override
	public void setText(String value) {
		getElement().setInnerText(value);
	}
}
