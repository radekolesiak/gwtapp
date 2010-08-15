package org.gwtapp.ccalc.client;

import org.gwtapp.ccalc.client.api.CCalcDownloadService;
import org.gwtapp.ccalc.client.api.CCalcDownloadServiceAsync;
import org.gwtapp.ccalc.client.api.CCalcService;
import org.gwtapp.ccalc.client.api.CCalcServiceAsync;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.ccalc.client.data.book.Currency;
import org.gwtapp.ccalc.client.ui.CCalcPanel;
import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.io.client.IORpcRequestBuilder;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class CCalc {

	public final static TemplateRepository templateService = new TemplateRepository(
			"/templates/");

	public final static CCalcServiceAsync service = GWT
			.create(CCalcService.class);

	public final static CCalcDownloadServiceAsync downloader = GWT
			.create(CCalcDownloadService.class);
	static {
		IORpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}

	public static void backup(Book book) {
		service.backup(book, CCalc
				.getAsyncCallback(new SimpleAsyncCallback<Void>()));
	}

	public final static String APPLICATION_DIV = "application";
	public final static String PAGE_DIV = "page";

	public static Currency defaultCurrency = Book.DEFAULT_CURRENCY.def();

	public static <T> AsyncCallback<T> getAsyncCallback(
			AsyncCallback<T> callback) {
		if (injector == null) {
			return callback;
		} else {
			return injector.create(callback);
		}
	}

	private static AsyncCallbackInjector injector = null;

	public static void doAppLoad(AsyncCallbackInjector injector) {
		CCalc.injector = injector;
		RootPanel.get(APPLICATION_DIV).add(new CCalcPanel());
	}
}
