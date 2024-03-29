package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.Date;

import org.gwtapp.core.rpc.data.HashModelData;

public class OperationImpl extends HashModelData implements Operation,
		Serializable {

	private static final long serialVersionUID = -7949899368363866233L;

	private String name = NAME.add(this).def();
	private Date date = DATE.add(this).def();
	private Double value = VALUE.add(this).def();
	private Double exchange = EXCHANGE.add(this).def();
	private Currency currency = CURRENCY.add(this).def();
	private FetchedRatio fetchedRatio = FETCHED_RATIO.add(this).def();

	public OperationImpl() {
	}

	public OperationImpl(String name, Date date, Double value, Double exchange,
			Currency cyrrency) {
		setName(name);
		setDate(date);
		setValue(value);
		setExchange(exchange);
		setCurrency(currency);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void setExchange(Double exchange) {
		this.exchange = exchange;
	}

	@Override
	public Double getExchange() {
		return exchange;
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
	public FetchedRatio getFetchedRatio() {
		return fetchedRatio;
	}

	@Override
	public void setFetchedRatio(FetchedRatio fetchedRatio) {
		this.fetchedRatio = fetchedRatio;
	}

}
