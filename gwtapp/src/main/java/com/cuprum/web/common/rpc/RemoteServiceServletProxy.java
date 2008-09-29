package com.cuprum.web.common.rpc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RPCServletUtils;

public class RemoteServiceServletProxy extends HttpServlet {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 7609350964035145107L;

	Logger LOGGER = Logger.getLogger(RemoteServiceServletProxy.class);

	public RemoteServiceServletProxy() {
		LOGGER.debug("I am here !!!!");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proxy(request, response);
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proxy(request, response);
	}

	// TODO: proxy requests errors
	public void proxy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LOGGER.debug("-------PROXY----------");

		LOGGER.debug("1: " + request.getMethod());
		LOGGER.debug("2: " + request.getQueryString());
		LOGGER.debug("3: " + request.getCharacterEncoding());
		LOGGER.debug("4: " + request.getContentType());
		LOGGER.debug("5: " + request.getContextPath());
		LOGGER.debug("6: " + request.getRequestURL());
		LOGGER.debug("7: " + request.getMethod());
		LOGGER.debug("8: " + request.getPathTranslated());

		try {
			String path = request.getRequestURL().toString();
			if (path.lastIndexOf("/") >= 0) {
				path = path.substring(path.lastIndexOf("/") + 1);
			}
			LOGGER.debug("9: " + path);
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod("http://gwtapp.cuprum.biz/"
					+ path);
			method.setQueryString(request.getQueryString());

			method.setRequestEntity(new StringRequestEntity(
					readContent(request), request.getContentType(), request
							.getCharacterEncoding()));
			if (client.executeMethod(method) == HttpStatus.SC_OK) {
				LOGGER.debug("Succes");
				writeResponse(request, response, method
						.getResponseBodyAsString());
			} else {
				LOGGER.error("Fail");
			}
		} catch (Throwable e) {
			LOGGER.error(e);
		}
	}

	protected boolean shouldCompressResponse(HttpServletRequest request,
			HttpServletResponse response, String responsePayload) {
		return RPCServletUtils
				.exceedsUncompressedContentLengthLimit(responsePayload);
	}

	protected String readContent(HttpServletRequest request)
			throws ServletException, IOException {
		return RPCServletUtils.readContentAsUtf8(request, true);
	}

	protected void writeResponse(HttpServletRequest request,
			HttpServletResponse response, String responsePayload)
			throws IOException {
		boolean gzipEncode = RPCServletUtils.acceptsGzipEncoding(request)
				&& shouldCompressResponse(request, response, responsePayload);

		RPCServletUtils.writeResponse(getServletContext(), response,
				responsePayload, gzipEncode);
	}
}
