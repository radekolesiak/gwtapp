package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.Date;

import org.gwtapp.ccalc.rpc.data.book.metafield.operation.CurrencyMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.DateMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.ExchangeMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.FetchedRatioMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.NameMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.ValueMetaField;
import org.gwtapp.core.rpc.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Operation extends IsSerializable, Serializable, ModelData {

	public final static NameMetaField NAME = new NameMetaField();
	public final static DateMetaField DATE = new DateMetaField();
	public final static ValueMetaField VALUE = new ValueMetaField();
	public final static ExchangeMetaField EXCHANGE = new ExchangeMetaField();
	public final static CurrencyMetaField CURRENCY = new CurrencyMetaField();
	public final static FetchedRatioMetaField FETCHED_RATIO = new FetchedRatioMetaField();

	void setName(String name);

	String getName();

	void setDate(Date date);

	Date getDate();

	void setValue(Double value);

	Double getValue();

	void setExchange(Double exchange);

	Double getExchange();

	void setCurrency(Currency currency);

	Currency getCurrency();

	FetchedRatio getFetchedRatio();

	void setFetchedRatio(FetchedRatio fetchedRatio);
}
