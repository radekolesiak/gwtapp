package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.Date;

import org.gwtapp.ccalc.rpc.data.book.metafield.operation.CurrencyMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.DateMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.operation.ExchangeMetaField;
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

	public void setName(String name);

	public String getName();

	public void setDate(Date date);

	public Date getDate();

	public void setValue(Double value);

	public Double getValue();

	public void setExchange(Double exchange);

	public Double getExchange();

	public void setCurrency(Currency currency);

	public Currency getCurrency();
}
