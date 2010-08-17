package org.gwtapp.ccalc.client.ui.calculations;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.data.book.Calculation;
import org.gwtapp.ccalc.client.proc.calculator.Calculator;
import org.gwtapp.extension.widgets.client.handler.fields.LabelHandler;
import org.gwtapp.form.client.ui.TemplateModelPanel;


public class CalculationsSummaryRowPanel extends
		TemplateModelPanel<Calculation> {

	private final LabelHandler<Double> diff = new LabelHandler<Double>();

	public CalculationsSummaryRowPanel() {
		super(CCalc.templateService
				.load("calculations/CalculationsSummaryRowPanel.jsp"));
		add(Calculation.FIFO_BASE, new LabelHandler<Double>());
		add(Calculation.INCOME, new LabelHandler<Double>());
		add(Calculation.COST, new LabelHandler<Double>());
		add("diff", diff);
	}

	@Override
	public void setValue(Calculation value, boolean fireEvents) {
		if (isTemplated()) {
			diff.getWidget().setValue(calculateDiff(value));
		}
		super.setValue(value, fireEvents);
	}

	private double calculateDiff(Calculation value) {
		Double fifo = value.getFifoBase();
		Double income = value.getIncome();
		Double cost = value.getCost();
		double diff = 0.0;
		if (fifo == null) {
			fifo = 0.0;
		}
		if (cost == null) {
			cost = 0.0;
		}
		if (income == null) {
			income = 0.0;
		}
		diff = Calculator.r(fifo - (income - cost));
		return diff;
	}
}
