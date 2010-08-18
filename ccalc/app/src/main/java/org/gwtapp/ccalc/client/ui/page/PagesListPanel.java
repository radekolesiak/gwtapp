package org.gwtapp.ccalc.client.ui.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.ui.DisclosurePanel;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsItemFormPanel;
import org.gwtapp.ccalc.client.ui.page.PageHeaderPanel.Model;
import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.ccalc.rpc.data.book.Page;
import org.gwtapp.ccalc.rpc.data.book.PageImpl;
import org.gwtapp.ccalc.rpc.proc.calculator.Calculator;
import org.gwtapp.template.client.Param;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class PagesListPanel extends TemplatePanel<List<Page>> {

	private final List<PageFormPanel> pageFormPanels = new ArrayList<PageFormPanel>();

	private final UiHandler<FlowPanel> pagesPanel = new UiHandler<FlowPanel>(
			new FlowPanel());

	private final UiHandler<PageHeaderPanel> headerPanel = new UiHandler<PageHeaderPanel>(
			new PageHeaderPanel(false));

	private List<Page> pages = new ArrayList<Page>();
	private Currency baseCurrency = null;

	public PagesListPanel() {
		super(CCalc.templateService.load("page/PagesListPanel.jsp"));
		add("headerPanel", headerPanel);
		add("pagesPanel", pagesPanel);
	}

	@Override
	public void onAddWidgets() {
		headerPanel.getWidget().addValueChangeHandler(
				new ValueChangeHandler<Model>() {
					@Override
					public void onValueChange(ValueChangeEvent<Model> event) {
						if (event.getValue().state == Model.State.ADD) {
							insertPage(0, createPage());
						}
					}
				});
	}

	private Page createPage() {
		Page page = new PageImpl();
		page.setName(new Date() + "");
		return page;
	}

	@Override
	public List<Page> getValue() {
		return pages;
	}

	@Override
	public void setValue(List<Page> value) {
		setValue(value, false);
	}

	@Override
	public void setValue(final List<Page> value, boolean fireEvents) {
		if (isTemplated()) {
			pagesPanel.getWidget().clear();
			pageFormPanels.clear();
			pages = new ArrayList<Page>();
			for (int i = 0; i < value.size(); i++) {
				insertPage(i, value.get(i));
			}
			calculate(value);
			if (fireEvents) {
				ValueChangeEvent.fire(this, value);
			}
		}
	}

	public void insertPage(final int index, final Page page) {
		final PageHeaderPanel header = new PageHeaderPanel(true);
		final PageFormPanel form = new PageFormPanel();
		final DisclosurePanel disclosure = new DisclosurePanel(
				CCalc.templateService.load("DisclosurePanel.jsp"), header, form);
		pagesPanel.getWidget().insert(disclosure, index);
		pageFormPanels.add(index, form);
		pages.add(index, page);
		header.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				disclosure.setValue(!disclosure.getValue(), true);
			}
		});
		header.addValueChangeHandler(new ValueChangeHandler<Model>() {
			@Override
			public void onValueChange(ValueChangeEvent<Model> event) {
				switch (event.getValue().state) {
				case ADD:
					insertPage(getRowIndex(disclosure) + 1, createPage());
					break;
				case REMOVE:
					if (Window.confirm(headerPanel.getParamMessage(
							"pageToRemove", new Param("name", page.getName())))) {
						removePage(getRowIndex(disclosure));
					}
					break;
				}
			}
		});
		disclosure.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (!form.isAnytimeDisplayed()) {
					form.setAnytimeDisplayed(true);
					form.setValue(page);
					calculate(getValue());
				}
				updateOpenedModeView(disclosure, header);
			}
		});
		form.addValueChangeHandler(new ValueChangeHandler<Page>() {
			@Override
			public void onValueChange(ValueChangeEvent<Page> event) {
				updatePageModelView(event.getValue(), disclosure, header);
				calculate(getValue());
			}
		});
		form.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				updatePrintModeView(disclosure, header, form);
			}
		});
		updatePageModelView(page, disclosure, header);
		updateOpenedModeView(disclosure, header);
	}

	private void updatePageModelView(Page page, DisclosurePanel disclosure,
			PageHeaderPanel header) {
		pages.set(getRowIndex(disclosure), page);
		header.setValue(new PageHeaderPanel.Model(page.getName()));
	}

	private void updateOpenedModeView(DisclosurePanel disclosure,
			PageHeaderPanel header) {
		if (disclosure.getValue()) {
			header.addStyleName(headerPanel.getMessage("openedStyle"));
			header.removeStyleName(headerPanel.getMessage("closedStyle"));
		} else {
			header.removeStyleName(headerPanel.getMessage("openedStyle"));
			header.addStyleName(headerPanel.getMessage("closedStyle"));
		}
	}

	private void updatePrintModeView(DisclosurePanel disclosure,
			PageHeaderPanel header, PageFormPanel form) {
		boolean printMode = form.isPrintMode();
		header.setVisible(!printMode);
		headerPanel.getWidget().setVisible(!printMode);
		for (int i = 0; i < pagesPanel.getWidget().getWidgetCount(); i++) {
			Widget panel = pagesPanel.getWidget().getWidget(i);
			boolean visible = (panel == disclosure) | (!printMode);
			panel.setVisible(visible);
		}
	}

	public void removePage(int index) {
		pagesPanel.getWidget().remove(index);
		pageFormPanels.remove(index);
		pages.remove(index);
	}

	private int getRowIndex(DisclosurePanel disclosurePanel) {
		return Math.max(-1, pagesPanel.getWidget().getWidgetIndex(
				disclosurePanel));
	}

	public void calculate() {
		calculate(getValue());
	}

	public void calculate(List<Page> pages) {
		List<Operation> operations = new ArrayList<Operation>();
		List<Integer> summariesPoints = new ArrayList<Integer>();
		for (Page page : pages) {
			summariesPoints.add(page.getOperations().size());
			operations.addAll(page.getOperations());
		}
		Calculator calculator = new Calculator(getBaseCurrency(),
				summariesPoints, operations);
		List<Calculation> calculations = calculator.getCalculations();
		List<Calculation> summaries = calculator.getSummaries();

		int calculationIndex = 0;
		for (int page = 0; page < pages.size(); page++) {
			PageFormPanel form = pageFormPanels.get(page);
			if (form.isAnytimeDisplayed()) {
				List<TemplateFormPanel<? extends Operation>> formPanelList = form
						.getRows();
				for (int i = 0; i < formPanelList.size(); i++) {
					Calculation calculation = calculations
							.get(calculationIndex++);
					CalculationsItemFormPanel itemPanel = (CalculationsItemFormPanel) formPanelList
							.get(i);
					itemPanel.setCalculationValue(calculation);
				}
				Calculation summary = summaries.get(page);
				form.setSummary(summary);
			} else {
				calculationIndex += summariesPoints.get(page);
			}
		}
	}

	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Currency getBaseCurrency() {
		return baseCurrency;
	}
}
