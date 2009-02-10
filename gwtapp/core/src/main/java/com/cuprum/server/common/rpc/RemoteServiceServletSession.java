package com.cuprum.server.common.rpc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cuprum.server.common.model.IModel;
import com.cuprum.server.common.utils.AbstractDAOMap;
import com.cuprum.server.common.utils.HibernateDAOMap;
import com.cuprum.server.common.utils.IDAO;
import com.cuprum.server.common.utils.RemoteServiceDAO;
import com.cuprum.web.common.client.SessionEntryPoint;
import com.cuprum.web.common.client.data.TConnectionSession;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * RemoteServiceServlet with session support.
 * 
 * @author Radek Olesiak
 * 
 */

public class RemoteServiceServletSession extends RemoteServiceServlet implements
		IsSerializable {

	/** UID. */
	private static final long serialVersionUID = -4539898520470145102L;

	private Logger LOGGER = Logger.getLogger(RemoteServiceServletSession.class);

	/**
	 * Reads connection session id from the query string.
	 * 
	 * @return Connection session.
	 */
	public final String getConnectionSession() {
		return getRequest().getParameter(
				TConnectionSession.CONNECTION_SESSION_REQUEST);
	}

	/**
	 * Reads module name from the query string.
	 * 
	 * @return Module name.
	 */
	public String getModuleName() {
		return getRequest().getParameter(SessionEntryPoint.MODULE_NAME_REQUEST);
	}

	protected synchronized IDAO<IModel> getDAO() {
		LOGGER.debug("Hibernate DAO module name: " + getModuleName());
		return HibernateDAOMap.DAO.getDAO(getModuleName());
	}

	public final <T extends IModel> T getBean(Class<T> c) {
		return getDAO().getBean(c);
	}

	private final static AbstractDAOMap<RemoteService> remoteServiceDAOMap = new AbstractDAOMap<RemoteService>() {
		@Override
		protected IDAO<RemoteService> createDAO() {
			return new RemoteServiceDAO();
		}
	};

	protected synchronized IDAO<RemoteService> getRemoteServiceDAO() {
		LOGGER.debug("RPC DAO module name: " + getModuleName());
		return remoteServiceDAOMap.getDAO(getModuleName());
	}

	public final <T extends RemoteService> T getRpcBean(Class<T> c) {
		return getRemoteServiceDAO().getBean(c);
	}

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return getThreadLocalRequest().getRequestURL().toString();
	}

	private ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

	public void setRequest(final HttpServletRequest request) {
		this.request.set(request);
	}

	public void setResponse(final HttpServletResponse response) {
		this.response.set(response);
	}

	public HttpServletRequest getRequest() {
		if (request.get() != null) {
			return request.get();
		} else {
			return getThreadLocalRequest();
		}
	}

	public HttpServletResponse getResponse() {
		if (response.get() != null) {
			return response.get();
		} else {
			return getThreadLocalResponse();
		}
	}

}
