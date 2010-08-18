package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gwtapp.core.rpc.data.HashModelData;

@SuppressWarnings("serial")
public class BookImpl extends HashModelData implements Book, Serializable {

	private String name = NAME.add(this).def();
	private String description = DESCRIPTION.add(this).def();
	private String mail = MAIL.add(this).def();
	private Currency baseCurrency = BASE_CURRENCY.add(this).def();
	private Currency defaultCurrency = DEFAULT_CURRENCY.add(this).def();
	private List<Page> pages = PAGES.add(this).def();
	private String currentVersion = CURRENT_VERSION.add(this).def();

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public Currency getBaseCurrency() {
		return baseCurrency;
	}

	@Override
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public List<Page> getPages() {
		return pages;
	}

	@Override
	public Currency getDefaultCurrency() {
		return defaultCurrency;
	}

	@Override
	public void setDefaultCurrency(Currency defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	@Override
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	@Override
	public String getCurrentVersion() {
		return currentVersion;
	}

	public static Book createDefault() {
		Book book = new BookImpl();
		book.setName("Book");
		{
			Page page = new PageImpl();
			page.setName(new Date() + "");
			// page.getOperations().add(new OperationImpl());
			// page.getOperations().add(new OperationImpl());
			// page.getOperations().add(new OperationImpl());
			book.getPages().add(page);
		}
		return book;
	}
}
