package org.gwtapp.template.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class WidgetWrapper extends Widget implements HasHTML, HasClickHandlers {

	public WidgetWrapper() {
		this(DOM.createDiv());
	}

	public WidgetWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public WidgetWrapper(Element e) {
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

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
}