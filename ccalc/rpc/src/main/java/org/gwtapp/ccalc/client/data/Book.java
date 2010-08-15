package org.gwtapp.ccalc.client.data;

import java.io.Serializable;
import java.util.List;

import org.gwtapp.ccalc.client.data.metafields.book.BaseCurrencyMetaField;
import org.gwtapp.ccalc.client.data.metafields.book.DefaultCurrencyMetaField;
import org.gwtapp.ccalc.client.data.metafields.book.DescriptionMetaField;
import org.gwtapp.ccalc.client.data.metafields.book.MailMetaField;
import org.gwtapp.ccalc.client.data.metafields.book.NameMetaField;
import org.gwtapp.ccalc.client.data.metafields.book.PagesMetaField;
import org.gwtapp.core.rpc.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Book extends ModelData, IsSerializable, Serializable {

	public final static NameMetaField NAME = new NameMetaField();
	public final static DescriptionMetaField DESCRIPTION = new DescriptionMetaField();
	public final static MailMetaField MAIL = new MailMetaField();
	public final static BaseCurrencyMetaField BASE_CURRENCY = new BaseCurrencyMetaField();
	public final static DefaultCurrencyMetaField DEFAULT_CURRENCY = new DefaultCurrencyMetaField();
	public final static PagesMetaField PAGES = new PagesMetaField();

	void setName(String name);

	String getName();

	void setDescription(String description);

	String getDescription();

	void setMail(String mail);

	String getMail();

	void setBaseCurrency(Currency baseCurrency);

	Currency getBaseCurrency();
	
	void setDefaultCurrency(Currency defaultCurrency);

	Currency getDefaultCurrency();	

	void setPages(List<Page> pages);

	List<Page> getPages();
}
