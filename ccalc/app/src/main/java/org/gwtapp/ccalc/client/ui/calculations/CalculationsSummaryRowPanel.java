package org.gwtapp.ccalc.client.ui.calculations;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.handler.fields.LabelHandler;
import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.proc.calculator.Calculator;
import org.gwtapp.form.client.ui.TemplateModelPanel;

public class CalculationsSummaryRowPanel extends TemplateModelPanel<Calculation> {

	private final LabelHandler<Double> diff = new LabelHandler<Double>();

	public CalculationsSummaryRowPanel() {
		super(CCalc.templateService.load("calculations/CalculationsSummaryRowPanel.jsp"));
		add(Calculation.FIFO_BASE, new LabelHandler<Double>());
		add(Calculation.INCOME, new LabelHandler<Double>());
		add(Calculation.COST, new LabelHandler<Double>());
		add("diff", diff);
	}

	@Override
	public void setTemplateValue(Calculation value) {
		diff.getWidget().setValue(Calculator.calculateDiff(value));
	}
}
