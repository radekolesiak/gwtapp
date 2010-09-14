package org.gwtapp.ccalc.server;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.gwtapp.ccalc.rpc.api.CCalcDownloadService;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.io.server.IODownloadHttpServlet;

import com.google.inject.Singleton;

@Singleton
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
				getThreadLocalResponse().setHeader(
						"Content-Disposition",
						"attachment; filename=\"" + encodeFilename(fileName)
								+ "\"");
				getThreadLocalResponse().getOutputStream().print(content);
				log.debug("Success download");
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	private String encodeFilename(String filename)
			throws UnsupportedEncodingException {
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		String encodedFileName = null;
		if (userAgent.contains("MSIE") || userAgent.contains("Opera")) {
			encodedFileName = URLEncoder.encode(filename, "UTF-8");
		} else {
			encodedFileName = "=?UTF-8?B?"
					+ new String(
							Base64.encodeBase64(filename.getBytes("UTF-8")),
							"UTF-8") + "?=";
		}
		return encodedFileName;
	}
}
