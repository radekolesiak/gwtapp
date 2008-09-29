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

import com.cuprum.web.common.rpc.RemoteServiceServletProxy;

public class ProxyFilter implements Filter {
	static Logger LOGGER = Logger.getLogger(ProxyFilter.class);

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest _request, ServletResponse _response,
			FilterChain chain) throws IOException, ServletException {
		if (_request instanceof HttpServletRequest) {
			LOGGER.debug("----FILTER-----");
			HttpServletRequest request = (HttpServletRequest) _request;
			HttpServletResponse response = (HttpServletResponse) _response;
			new RemoteServiceServletProxy().proxy(request, response);
		}
	}
}
