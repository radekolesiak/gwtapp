package org.gwtapp.ccalc.client.ui.book;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.ui.page.PagesListPanel;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.BookImpl;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.extension.widget.client.ui.fields.EnumField;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.handler.TextBoxHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class BookPanel extends TemplateModelPanel<Book> {

	private final TextBoxHandler name = new TextBoxHandler();
	private final TextBoxHandler description = new TextBoxHandler();
	private final TextBoxHandler mail = new TextBoxHandler();
	private final UiHandler<EnumField<Currency>> baseCurrency = //
	new UiHandler<EnumField<Currency>>(new EnumField<Currency>(Currency
			.values()));
	private final UiHandler<EnumField<Currency>> defaultCurrency = //
	new UiHandler<EnumField<Currency>>(new EnumField<Currency>(Currency
			.values()));
	private final UiHandler<PagesListPanel> pages = //
	new UiHandler<PagesListPanel>(new PagesListPanel());

	public BookPanel() {
		this(new BookImpl());
	}

	public BookPanel(Book value) {
		super(CCalc.templateService.load("book/BookPanel.jsp"), value);
		add(Book.NAME, name);
		add(Book.DESCRIPTION, description);
		add(Book.MAIL, mail);
		add(Book.BASE_CURRENCY, baseCurrency);
		add(Book.DEFAULT_CURRENCY, defaultCurrency);
		add(Book.PAGES, pages);
	}

	@Override
	public void onAddFormWidgets() {
		pages.getWidget().setBaseCurrency(getValue().getBaseCurrency());
		baseCurrency.getWidget().addValueChangeHandler(
				new ValueChangeHandler<Currency>() {
					@Override
					public void onValueChange(ValueChangeEvent<Currency> event) {
						updateCalculations(event.getValue());
					}
				});
		defaultCurrency.getWidget().addValueChangeHandler(
				new ValueChangeHandler<Currency>() {
					@Override
					public void onValueChange(ValueChangeEvent<Currency> event) {
						updateDefaultCurrency(event.getValue());
					}
				});
	}

	@Override
	public void setValue(Book value, boolean fireEvents) {
		if (isTemplated()) {
			updateBaseCurrencyStyle(value.getBaseCurrency());
			updateDefaultCurrency(value.getDefaultCurrency());
		}
		super.setValue(value, fireEvents);
	}

	private void updateCalculations(Currency baseCurrency) {
		updateBaseCurrencyStyle(baseCurrency);
		pages.getWidget().setBaseCurrency(baseCurrency);
		pages.getWidget().calculate();
	}

	private void updateBaseCurrencyStyle(Currency baseCurrency) {
		for (Currency currency : Currency.values()) {
			removeStyleName(getBaseCurrencyStyleClass() + currency);
		}
		addStyleName(getBaseCurrencyStyleClass() + baseCurrency);
	}

	private String getBaseCurrencyStyleClass() {
		return baseCurrency.getMessage("BaseCurrencyStyleClass");
	}

	private void updateDefaultCurrency(Currency value) {
		CCalc.defaultCurrency = value;
	}
}
