package org.gwtapp.ccalc.client.ui;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.ui.book.BookUploadPanel;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.BookImpl;
import org.gwtapp.template.client.handler.UiHandler;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;

public class CCalcPanel extends TemplatePanel<Book> {

	private final WidgetHandler newButton = //
	new WidgetHandler();

	private final UiHandler<BookUploadPanel> uploadPanel = //
	new UiHandler<BookUploadPanel>(new BookUploadPanel());

	private final UiHandler<ContentPanel> contentPanel = //
	new UiHandler<ContentPanel>(CCalc.getGinjector().getContentPanel());

	public CCalcPanel() {
		super(CCalc.templateService.load("CCalcPanel.jsp"));
		add("uploadPanel", uploadPanel);
		add("newButton", newButton);
		add("contentPanel", contentPanel);
	}

	@Override
	public void onWidgets() {
		uploadPanel.getWidget().addValueChangeHandler(
				new ValueChangeHandler<Book>() {
					@Override
					public void onValueChange(ValueChangeEvent<Book> event) {
						contentPanel.getWidget().setValue(event.getValue());
					}
				});
		newButton.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (Window.confirm(newButton.getMessage("onNew"))) {
					contentPanel.getWidget().setValue(BookImpl.createDefault());
				}
			}
		});
	}

	@Override
	public Book getValue() {
		return contentPanel.getWidget().getValue();
	}
}
