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
import org.gwtapp.ccalc.rpc.data.book.FetchedRatio;
import org.gwtapp.ccalc.rpc.data.book.FetchedRatioImpl;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.ccalc.rpc.data.book.OperationImpl;
import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.FifoMetaField;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.core.client.pipe.PipeHandler;
import org.gwtapp.core.client.pipe.PipeManager;
import org.gwtapp.core.rpc.data.AbstractModelData;
import org.gwtapp.extension.widget.client.handler.DatePickerHandler;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class CalculationsItemFormPanel extends TemplateModelPanel<Operation> {

	private final static long DAY = 24L * 60L * 60L * 1000L;

	public static enum State {
		NONE, ADD, REMOVE;
	}

	private static enum FetchRatioState {
		FIELDS_INCOMPLETE, FETCHING, SAME, DIFFERENT, FAILURE
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
				updateFetchedRatioState();
			}
		};
		getPipeManager().addPipe(BaseCurrencyPipe.class,
				new BaseCurrencyPipe(pipeHandler));
		addValueChangeHandler(new ValueChangeHandler<Operation>() {
			@Override
			public void onValueChange(ValueChangeEvent<Operation> event) {
				updateFetchedRatioState();
			}
		});
		updateFetchedRatioState();
	}

	@SuppressWarnings("deprecation")
	private void fetchRatio() {
		final Date date = getField(Calculation.DATE).getValue();
		final Currency from = getField(Calculation.CURRENCY).getValue();
		if (date != null && from != null) {
			setFetchRatioState(FetchRatioState.FETCHING);
			Date shifted = new Date(date.getTime() - DAY);
			int year = shifted.getYear() + 1900;
			int month = shifted.getMonth() + 1;
			int day = shifted.getDate();
			CCalc.ccalc.getRatio(year, month, day, from, getBaseCurrency(),
					new SimpleAsyncCallback<Double>() {
						@Override
						public void onSuccess(Double result) {
							setCurrencyRatio(date, from, result);
						}

						@Override
						public void onFailure(Throwable e) {
							setFetchRatioState(FetchRatioState.FAILURE);
						}
					});
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

	private void setCurrencyRatio(Date date, Currency currency, Double ratio) {
		FetchedRatio fetched = new FetchedRatioImpl(date, currency, ratio);
		Operation operation = getValue();
		operation.setFetchedRatio(fetched);
		if (ratio != null) {
			getField(Calculation.EXCHANGE).setValue(ratio, true);
		}
		updateFetchedRatioState();
	}

	private void updateFetchedRatioState() {
		Operation operation = getValue();
		if (operation == null || operation.getDate() == null
				|| getBaseCurrency() == null || operation.getCurrency() == null) {
			setFetchRatioState(FetchRatioState.FIELDS_INCOMPLETE);
		} else {
			FetchedRatio fetched = operation.getFetchedRatio();
			boolean equals = fetched != null;
			if (fetched != null) {
				equals &= AbstractModelData.equalsAB(fetched.getDate(),
						operation.getDate());
				equals &= AbstractModelData.equalsAB(fetched.getBaseCurrency(),
						getBaseCurrency());
				equals &= AbstractModelData.equalsAB(fetched.getCurrency(),
						operation.getCurrency());
				equals &= AbstractModelData.equalsAB(r(fetched.getRatio()),
						r(operation.getExchange()));
			}
			setFetchRatioState(equals ? FetchRatioState.SAME
					: FetchRatioState.DIFFERENT);
		}
	}

	private void setFetchRatioState(FetchRatioState fetchRatioState) {
		ratio.getWidget().setHTML(
				ratio.getMessage(fetchRatioState.name().toLowerCase()));
	}

	private Currency getBaseCurrency() {
		return PipeManager.getBroadcastValue(BaseCurrencyPipe.class);
	}

	private Double r(Double v) {
		if (v == null) {
			return null;
		}
		return Math.round(v * 1e8) / 1e8;
	}
}
