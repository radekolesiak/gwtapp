package com.cuprum.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RPCServletUtils;

public class ProxyFilter implements Filter {
	static Logger LOGGER = Logger.getLogger(ProxyFilter.class);

	public final static String PARAM_SERVER = "server";

	private String server = null;

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
		if (config != null) {
			setServer(config.getInitParameter(PARAM_SERVER));
		}
	}

	public void doFilter(ServletRequest _request, ServletResponse _response,
			FilterChain chain) throws IOException, ServletException {
		if (_request instanceof HttpServletRequest) {
			LOGGER.debug("----FILTER-----");
			HttpServletRequest request = (HttpServletRequest) _request;
			HttpServletResponse response = (HttpServletResponse) _response;
			proxy(request, response, getServer());
		}
	}

	protected void setServer(String server) {
		this.server = server;
	}

	public String getServer() {
		return server;
	}

	// TODO: proxy requests errors
	public void proxy(HttpServletRequest request,
			HttpServletResponse response, String server)
			throws ServletException, IOException {

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
			PostMethod method = new PostMethod(server + path);
			method.setQueryString(request.getQueryString());

			method.setRequestEntity(new StringRequestEntity(
					readContent(request), request.getContentType(), request
							.getCharacterEncoding()));
			try {
				if (client.executeMethod(method) == HttpStatus.SC_OK) {
					LOGGER.debug("Succes");
					LOGGER.debug(method.getResponseBodyAsString());
					for (Header header : method.getResponseHeaders()) {
						response.setHeader(header.getName(), header.getValue());
					}
					response.getWriter()
							.write(method.getResponseBodyAsString());
				} else {
					LOGGER.error("Fail");
				}
			} finally {
				method.releaseConnection();
			}
		} catch (Throwable e) {
			LOGGER.error(e);
		}
	}

	protected String readContent(HttpServletRequest request)
			throws ServletException, IOException {
		return RPCServletUtils.readContentAsUtf8(request, true);
	}
}
