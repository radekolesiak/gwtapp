package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.Date;

import org.gwtapp.ccalc.rpc.data.book.metafield.book.BaseCurrencyMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio.CurrencyMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio.DateMetaField;
import org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio.RatioMetaField;
import org.gwtapp.core.rpc.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface FetchedRatio extends IsSerializable, Serializable, ModelData {

	public final static DateMetaField DATE = new DateMetaField();
	public final static BaseCurrencyMetaField BASE_CURRENCY = new BaseCurrencyMetaField();
	public final static CurrencyMetaField CURRENCY = new CurrencyMetaField();
	public final static RatioMetaField RATIO = new RatioMetaField();

	void setDate(Date date);

	Date getDate();

	void setBaseCurrency(Currency currency);

	Currency getBaseCurrency();

	void setCurrency(Currency currency);

	Currency getCurrency();

	void setRatio(Double ratio);

	Double getRatio();
}
