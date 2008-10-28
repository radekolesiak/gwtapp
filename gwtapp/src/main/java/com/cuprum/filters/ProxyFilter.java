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

import org.apache.log4j.Logger;

import com.cuprum.server.common.rpc.RemoteServiceServletProxy;

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
			RemoteServiceServletProxy.proxy(request, response, getServer());
		}
	}

	protected void setServer(String server) {
		this.server = server;
	}

	public String getServer() {
		return server;
	}
}
