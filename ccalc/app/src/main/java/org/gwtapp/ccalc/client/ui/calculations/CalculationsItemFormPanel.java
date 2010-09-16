package org.gwtapp.ccalc.client.ui.calculations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.handler.fields.DoubleNumberHandler;
import org.gwtapp.ccalc.client.handler.fields.EnumHandler;
import org.gwtapp.ccalc.client.handler.fields.ExchangeLabelHandler;
import org.gwtapp.ccalc.client.handler.fields.LabelHandler;
import org.gwtapp.ccalc.client.pipe.BaseCurrencyPipe;
import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.data.book.CalculationImpl;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.ccalc.rpc.data.book.OperationImpl;
import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.FifoMetaField;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.core.client.pipe.PipeHandler;
import org.gwtapp.core.client.pipe.PipeManager;
import org.gwtapp.extension.widget.client.handler.DatePickerHandler;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class CalculationsItemFormPanel extends TemplateModelPanel<Operation> {

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
	private final WidgetHandler ratio = new WidgetHandler();

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
		add("ratio", ratio);
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
		ratio.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fetchRatio();
			}
		});
		PipeHandler<Currency> pipeHandler = new PipeHandler<Currency>() {
			@Override
			public void onChangeValue(Currency value) {
				System.out.println("PIPE RECEIVED: " + value);
			}
		};
		getPipeManager().addPipe(BaseCurrencyPipe.class,
				new BaseCurrencyPipe(pipeHandler));
	}

	private void fetchRatio() {
		Date date = getField(Calculation.DATE).getValue();
		Currency from = getField(Calculation.CURRENCY).getValue();
		if (date != null && from != null) {
			CCalc.ccalc.getRatio(date, from,
					PipeManager.getBroadcastValue(BaseCurrencyPipe.class),
					create(new SimpleAsyncCallback<Double>() {
						@Override
						public void onSuccess(Double result) {
							setCurrencyRatio(result);
						}
					}));
		}
	}

	private void setCurrencyRatio(Double ratio) {
		if (ratio != null) {
			getField(Calculation.EXCHANGE).setValue(ratio, true);
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
