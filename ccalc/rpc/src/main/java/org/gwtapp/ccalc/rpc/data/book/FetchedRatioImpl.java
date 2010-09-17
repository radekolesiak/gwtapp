package org.gwtapp.ccalc.rpc.data.book;

import java.util.Date;

import org.gwtapp.core.rpc.data.HashModelData;

public class FetchedRatioImpl extends HashModelData implements FetchedRatio {

	private Date date = DATE.add(this).def();
	private Currency currency = CURRENCY.add(this).def();
	private Double ratio = RATIO.add(this).def();

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Override
	public Double getRatio() {
		return ratio;
	}
}
