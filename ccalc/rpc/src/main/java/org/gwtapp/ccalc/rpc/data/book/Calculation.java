package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;

import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.ComponentMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.CostMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.FifoBaseMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.FifoMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.calculation.IncomeMetaField;


public interface Calculation extends Operation, Serializable {

	public final static ComponentMetaField COMPONENT = new ComponentMetaField();
	public final static FifoMetaField[] FIFO = FifoMetaField.getFields();
	public final static FifoBaseMetaField FIFO_BASE = new FifoBaseMetaField();
	public final static IncomeMetaField INCOME = new IncomeMetaField();
	public final static CostMetaField COST = new CostMetaField();

	void setFifo(Currency currency, Double fifo);

	Double getFifo(Currency currency);

	void setComponent(Double component);

	Double getComponent();

	void setFifoBase(Double value);

	Double getFifoBase();

	void setIncome(Double value);

	Double getIncome();

	void setCost(Double value);

	Double getCost();
}
