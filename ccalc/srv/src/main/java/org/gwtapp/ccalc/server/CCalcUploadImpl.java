package org.gwtapp.ccalc.server;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.io.server.IOUploadHttpServlet;

@SuppressWarnings("serial")
public class CCalcUploadImpl extends IOUploadHttpServlet<Book> {

	private final static Logger log = Logger.getLogger(CCalcUploadImpl.class);

	public final static int MAX_SIZE = 10 * 1024 * 1024;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload upload = new ServletFileUpload();
				upload.setSizeMax(MAX_SIZE);
				FileItemIterator it = upload.getItemIterator(request);
				if (it.hasNext()) {
					FileItemStream item = it.next();
					InputStream input = item.openStream();
					try {
						if (!item.isFormField()) {
							String xml = getXML(item);
							xml = StringEscapeUtils.unescapeJava(xml);
							Book book = Utils.decode(xml.getBytes());
							if (book != null) {
								log.debug("Success upload");
								doSuccess(response, book);
								return;
							} else {
								throw new RuntimeException("Invalid file: "
										+ item.getName());
							}
						}
					} finally {
						IOUtils.closeQuietly(input);
					}
				}
			}
			throw new RuntimeException("File not found");
		} catch (Exception e) {
			log.error("error", e);
			doFailure(response, new RpcException());
		}
	}

	protected String getXML(FileItemStream item) throws IOException {
		InputStream input = item.openStream();
		return new String(IOUtils.toByteArray(input));
	}
}
