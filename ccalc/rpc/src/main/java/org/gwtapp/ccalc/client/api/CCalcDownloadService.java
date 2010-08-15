package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.book.Book;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc-download.rpc")
public interface CCalcDownloadService extends RemoteService {

	void download(Book book);

}
