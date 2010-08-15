package org.gwtapp.ccalc.server;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.gwtapp.ccalc.client.api.CCalcDownloadService;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.io.server.IODownloadHttpServlet;


public class CCalcDownloadImpl extends IODownloadHttpServlet implements
		CCalcDownloadService {

	private final static Logger log = Logger.getLogger(CCalcDownloadImpl.class);

	private static final long serialVersionUID = -6997863968216005596L;

	@Override
	public void download(Book book) {
		try {
			if (book != null) {
				String content = new String(Utils.encode(book));
				content = StringEscapeUtils.escapeJava(content);
				String fileName = Utils.getFilename(book);
				getThreadLocalResponse().setContentType(Utils.CONTENT_TYPE);
				getThreadLocalResponse().setContentLength(content.length());
				getThreadLocalResponse().setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
				getThreadLocalResponse().getOutputStream().print(content);
				log.debug("Success download");
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
