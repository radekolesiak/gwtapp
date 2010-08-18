package org.gwtapp.ccalc.rpc.api;

import org.gwtapp.ccalc.rpc.data.book.Book;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc-download.rpc")
public interface CCalcDownloadService extends RemoteService {

	void download(Book book);

}
