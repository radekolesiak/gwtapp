package org.gwtapp.ccalc.client.ui.calculations;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.ccalc.client.data.book.Calculation;
import org.gwtapp.ccalc.client.data.book.CalculationImpl;
import org.gwtapp.ccalc.client.data.book.Operation;
import org.gwtapp.ccalc.client.ui.form.ListFormPanel;


public class CalculationsListFormPanel extends ListFormPanel<Operation> {

	public CalculationsListFormPanel(RowCallback<Operation> rowCallback) {
		super(rowCallback);
	}

	@Override
	public void setValue(List<Operation> value, boolean fireEvents) {
		List<Operation> calculations = new ArrayList<Operation>();
		for (Operation operation : value) {
			if (!(operation instanceof Calculation)) {
				operation = new CalculationImpl(operation);
			}
			calculations.add(operation);
		}
		super.setValue(calculations, fireEvents);
	}
}
