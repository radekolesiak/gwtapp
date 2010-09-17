package org.gwtapp.ccalc.rpc.data.book;

import java.util.Date;

import org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio.CurrencyMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio.DateMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio.RatioMetaField;

public interface FetchedRatio {

	public final static DateMetaField DATE = new DateMetaField();
	public final static CurrencyMetaField CURRENCY = new CurrencyMetaField();
	public final static RatioMetaField RATIO = new RatioMetaField();

	void setDate(Date date);

	Date getDate();

	void setCurrency(Currency currency);

	Currency getCurrency();

	void setRatio(Double ratio);

	Double getRatio();
}
