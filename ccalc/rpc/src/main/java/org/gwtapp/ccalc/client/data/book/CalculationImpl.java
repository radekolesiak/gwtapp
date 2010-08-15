package org.gwtapp.ccalc.client.data.book;

import java.io.Serializable;

public class CalculationImpl extends OperationImpl implements Calculation,
		Serializable {

	private static final long serialVersionUID = -8683906607644469730L;

	private Double component = COMPONENT.add(this).def();
	private Double fifoBase = FIFO_BASE.add(this).def();
	private Double income = INCOME.add(this).def();
	private Double cost = COST.add(this).def();
	private Double[] fifo = new Double[Currency.values().length];
	{
		for (Currency currency : Currency.values()) {
			fifo[currency.ordinal()] = FIFO[currency.ordinal()].add(this).def();
		}
	}

	public CalculationImpl() {
	}

	public CalculationImpl(Operation operation) {
		for (String property : operation.getPropertyNames()) {
			set(property, operation.get(property));
		}
	}

	@Override
	public Double getFifo(Currency currency) {
		if (currency != null) {
			return fifo[currency.ordinal()];
		} else {
			return null;
		}
	}

	@Override
	public void setFifo(Currency currency, Double value) {
		if (currency != null) {
			fifo[currency.ordinal()] = value;
		}
	}

	@Override
	public void setComponent(Double component) {
		this.component = component;
	}

	@Override
	public Double getComponent() {
		return component;
	}

	@Override
	public void setFifoBase(Double fifoBase) {
		this.fifoBase = fifoBase;
	}

	@Override
	public Double getFifoBase() {
		return fifoBase;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getIncome() {
		return income;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getCost() {
		return cost;
	}
}
