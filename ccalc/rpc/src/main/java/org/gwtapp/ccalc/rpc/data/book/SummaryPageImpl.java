package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.List;

import org.gwtapp.core.rpc.data.HashModelData;

public class SummaryPageImpl extends HashModelData implements SummaryPage,
		Serializable {

	private static final long serialVersionUID = 7457370886574568947L;

	private Integer id = ID.add(this).def();
	private List<Calculation> calculations = CALCULATIONS.add(this).def();
	private List<Calculation> summary = SUMMARY.add(this).def();

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setCalculations(List<Calculation> calculations) {
		this.calculations = calculations;
	}

	@Override
	public List<Calculation> getCalculations() {
		return calculations;
	}

	@Override
	public void setSummary(List<Calculation> summary) {
		this.summary = summary;
	}

	@Override
	public List<Calculation> getSummary() {
		return summary;
	}
}
