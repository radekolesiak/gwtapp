package org.gwtapp.ccalc.client.ui.calculations;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.data.Calculation;
import org.gwtapp.ccalc.client.data.CalculationImpl;
import org.gwtapp.ccalc.client.data.Currency;
import org.gwtapp.ccalc.client.data.Operation;
import org.gwtapp.ccalc.client.data.OperationImpl;
import org.gwtapp.ccalc.client.data.metafields.calculation.FifoMetaField;
import org.gwtapp.ccalc.client.handler.fields.ExchangeLabelHandler;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.widgets.client.handler.DatePickerHandler;
import org.gwtapp.widgets.client.handler.fields.DoubleNumberHandler;
import org.gwtapp.widgets.client.handler.fields.EnumHandler;
import org.gwtapp.widgets.client.handler.fields.LabelHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class CalculationsItemFormPanel extends
		TemplateModelPanel<Operation> {

	public static enum State {
		NONE, ADD, REMOVE;
	}

	private final static List<String> CALCULATION_FIELDS_ONLY = new ArrayList<String>();
	static {
		CALCULATION_FIELDS_ONLY
				.addAll(new CalculationImpl().getPropertyNames());
		CALCULATION_FIELDS_ONLY.removeAll(new OperationImpl()
				.getPropertyNames());
	}

	private final static String template = "calculations/CalculationsItemFormPanel.jsp";

	private final LabelHandler<Double> component = new LabelHandler<Double>();
	private final WidgetHandler add = new WidgetHandler();
	private final WidgetHandler remove = new WidgetHandler();

	private State state = State.NONE;

	private Double componentValue = null;

	public CalculationsItemFormPanel() {
		super(CCalc.templateService.load(template));
		add(Calculation.NAME, new TextBoxHandler());
		add(Calculation.DATE, new DatePickerHandler());
		add(Calculation.VALUE, new DoubleNumberHandler());
		add(Calculation.EXCHANGE, new DoubleNumberHandler());
		add(Calculation.CURRENCY, new EnumHandler<Currency>(Currency.values()));
		add(Calculation.COMPONENT, this.component);
		for (FifoMetaField field : Calculation.FIFO) {
			add(field, new ExchangeLabelHandler());
		}
		add(Calculation.FIFO_BASE, new LabelHandler<Double>());
		add(Calculation.INCOME, new LabelHandler<Double>());
		add(Calculation.COST, new LabelHandler<Double>());
		add("add", add);
		add("remove", remove);
	}

	@Override
	public void onAddFormWidgets() {
		super.onAddFormWidgets();
		add.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setState(State.ADD, true);
			}
		});
		remove.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setState(State.REMOVE, true);
			}
		});
		if (componentValue != null) {
			setComponentValue(componentValue);
		}
	}

	public void setComponentValue(Double value) {
		if (isTemplated()) {
			component.getWidget().setValue(value);
		} else {
			componentValue = value;
		}
	}

	@SuppressWarnings("unchecked")
	public void setCalculationValue(final Calculation value) {
		for (final String field : CALCULATION_FIELDS_ONLY) {
			getField(field).setValue(value.get(field));
		}
	}

	public State getState() {
		return state;
	}

	private void setState(State state, boolean fireEvents) {
		this.state = state;
		if (fireEvents) {
			fireChangeEvent();
		}
	}
}
