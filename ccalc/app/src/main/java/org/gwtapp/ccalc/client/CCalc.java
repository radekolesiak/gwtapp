package org.gwtapp.ccalc.client;

import java.util.Date;

import org.gwtapp.ccalc.client.ui.CCalcPanel;
import org.gwtapp.ccalc.rpc.api.CCalcDownloadService;
import org.gwtapp.ccalc.rpc.api.CCalcDownloadServiceAsync;
import org.gwtapp.ccalc.rpc.api.CCalcService;
import org.gwtapp.ccalc.rpc.api.CCalcServiceAsync;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.api.UserServiceAsync;
import org.gwtapp.io.client.IORpcRequestBuilder;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class CCalc {

	public final static TemplateRepository templateService = new TemplateRepository(
			"/templates/");

	public final static CCalcServiceAsync ccalc = GWT
			.create(CCalcService.class);

	public final static UserServiceAsync user = GWT.create(UserService.class);

	public final static CCalcDownloadServiceAsync downloader = GWT
			.create(CCalcDownloadService.class);
	static {
		IORpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}

	public static void backup(Book book) {
		ccalc.backup(book, new SimpleAsyncCallback<Void>());
	}

	public final static String APPLICATION_DIV = "application";
	public final static String PAGE_DIV = "page";

	public static Currency defaultCurrency = Book.DEFAULT_CURRENCY.def();

	private static CCalcGinjector ginjector = null;
	private static CCalcPanel panel = null;

	public static CCalcGinjector getGinjector() {
		return ginjector;
	}

	public static void getRatio(Date date, Currency from,
			AsyncCallback<Double> callback) {
		Currency to = panel.getValue().getBaseCurrency();
		CCalc.ccalc.getRatio(date, from, to, callback);
	}

	public static void doLoadCCalc(CCalcGinjector ginjector) {
		CCalc.ginjector = ginjector;
		CCalc.panel = ginjector.getCCalcPanel();
		RootPanel.get(APPLICATION_DIV).add(panel);
	}
}
