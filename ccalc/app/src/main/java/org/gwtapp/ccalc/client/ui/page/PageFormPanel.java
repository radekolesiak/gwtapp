package org.gwtapp.ccalc.client.ui.page;

import java.util.List;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsHeaderFormPanel;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsItemFormPanel;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsListFormPanel;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsSummaryRowPanel;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsHeaderFormPanel.State;
import org.gwtapp.ccalc.client.ui.form.ListFormPanel;
import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.data.book.CalculationImpl;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.ccalc.rpc.data.book.Page;
import org.gwtapp.ccalc.rpc.data.book.PageImpl;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.UiHandler;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;
import org.gwtapp.template.client.ui.TemplateModelPanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class PageFormPanel extends TemplateModelPanel<Page> implements
		HasChangeHandlers {

	private final ListFormPanel.RowCallback<Operation> callback = new ListFormPanel.RowCallback<Operation>() {
		@Override
		public Widget createHeaderFormPanel() {
			final CalculationsHeaderFormPanel headerPanel = new CalculationsHeaderFormPanel();
			headerPanel.addValueChangeHandler(new ValueChangeHandler<State>() {
				@Override
				public void onValueChange(ValueChangeEvent<State> event) {
					if (event.getValue() == State.ADD) {
						addRow(headerPanel);
					}
				}
			});
			return headerPanel;
		}

		@Override
		public TemplateFormPanel<? extends Operation> createRowFormPanel(int row) {
			final CalculationsItemFormPanel rowPanel = CCalc.getGinjector()
					.getCalculationsItemFormPanel();
			rowPanel.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					CalculationsItemFormPanel.State state = rowPanel.getState();
					if (state == CalculationsItemFormPanel.State.ADD) {
						addRow(rowPanel);
					} else if (state == CalculationsItemFormPanel.State.REMOVE) {
						removeRow(rowPanel);
					}
				}
			});
			return rowPanel;
		}
	};

	private boolean printMode = false;
	private boolean anytimeDisplayed = false;

	private final UiHandler<CalculationsListFormPanel> operations = new UiHandler<CalculationsListFormPanel>(
			new CalculationsListFormPanel(callback));
	private final UiHandler<CalculationsSummaryRowPanel> summary = new UiHandler<CalculationsSummaryRowPanel>(
			new CalculationsSummaryRowPanel());
	private final WidgetHandler print = new WidgetHandler();

	public PageFormPanel() {
		this(new PageImpl());
	}

	public PageFormPanel(Page value) {
		super(CCalc.templateService.load("page/PageFormPanel.jsp"), value);
		add("print", print);
		add(Page.NAME, new TextBoxHandler());
		add(Page.OPERATIONS, operations);
		add("summary", summary);
	}

	@Override
	public void onFormWidgets() {
		print.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setPrintMode(!isPrintMode());
				fireChangeEvent();
			}
		});
		setPrintMode(isPrintMode());
	}

	public boolean isPrintMode() {
		return printMode;
	}

	protected void setPrintMode(boolean printState) {
		this.printMode = printState;
		String allModeStyleName = print.getMessage("allMode");
		String printModeStyleName = print.getMessage("printMode");
		if (printState) {
			print.getWidget().addStyleName(printModeStyleName);
			print.getWidget().removeStyleName(allModeStyleName);
		} else {
			print.getWidget().removeStyleName(printModeStyleName);
			print.getWidget().addStyleName(allModeStyleName);
		}
	}

	public List<TemplateFormPanel<? extends Operation>> getRows() {
		return operations.getWidget().getRows();
	}

	public void setSummary(Calculation value) {
		summary.getWidget().setValue(value);
	}

	private void addRow(Widget owner) {
		Calculation value = new CalculationImpl();
		value.setCurrency(CCalc.defaultCurrency);
		operations.getWidget().insertRow(
				operations.getWidget().getRowIndex(owner) + 1, value);
	}

	private void removeRow(Widget owner) {
		if (Window.confirm(operations.getMessage("rowToRemove"))) {
			operations.getWidget().removeRow(
					operations.getWidget().getRowIndex(owner));
			ValueChangeEvent.fire(PageFormPanel.this, getValue());
		}
	}

	public void setAnytimeDisplayed(boolean anytimeDisplayed) {
		this.anytimeDisplayed = anytimeDisplayed;
	}

	public boolean isAnytimeDisplayed() {
		return anytimeDisplayed;
	}
}
