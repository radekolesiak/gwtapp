package com.cuprum.web.common.client;

import java.util.LinkedList;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * 
 * Class wich makes semiautomatically servlet connections.
 * 
 * @author radek
 * 
 */
public class EndPoint {
	protected String genQuery(String query, String param) {
		if (param != null && query != null) {
			if (query.length() == 0) {
				query += "?";
			} else {
				query += "&";
			}
			query += param;
		}
		return query;
	}

	protected LinkedList<String> getQueries() {
		LinkedList<String> list = new LinkedList<String>();
		if (TConnectionSession.getSession() != null) {
			list.add(TConnectionSession.CONNECTION_SESSION_REQUEST + "="
					+ TConnectionSession.getSession().get());
		}
		return list;
	}

	/**
	 * Setups end point.
	 * 
	 * @param endpoint
	 *            End point to setup.
	 * @param service
	 *            Service name.
	 * @return Returns the endpoint the same as argument.
	 */
	protected final <T> T get(final T endpoint, final String service) {
		String moduleRelativeURL = GWT.getModuleBaseURL() + service;
		String query = "";
		LinkedList<String> queries = getQueries();
		for (String q : queries) {
			query = genQuery(query, q);
		}
		((ServiceDefTarget) endpoint).setServiceEntryPoint(moduleRelativeURL
				+ query);
		return endpoint;

	}

	/**
	 * Setups end point for default service by end point owner class.
	 * 
	 * @param endpoint
	 *            End point to setup.
	 * @return Returns the endpoint the same as argument.
	 */
	protected final <T> T get(final T endpoint) {
		String name = endpoint.getClass().getName();

		try {
			// name = name.substring(name.lastIndexOf('.') + 1);
			try {
				name = name.substring(0, name.indexOf("_Proxy"));
			} catch (Exception e) {
				name = name + "";
			}
			return get(endpoint, name);
		} catch (Exception e) {
			name = name + "";
		}

		return endpoint;
	}

	private final static EndPoint sessionEndPoint = new EndPoint();

	public static <T> T create(final T endpoint, final String service) {
		return sessionEndPoint.get(endpoint, service);
	}

	public static <T> T create(final T endpoint) {
		return sessionEndPoint.get(endpoint);
	}
}
