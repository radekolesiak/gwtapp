package com.cuprum.server.common.rpc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cuprum.server.common.model.IModel;
import com.cuprum.server.common.utils.AbstractDAOMap;
import com.cuprum.server.common.utils.HibernateDAOMap;
import com.cuprum.server.common.utils.IDAO;
import com.cuprum.server.common.utils.RemoteServiceDAO;
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
	 * Reads connection session id from query string.
	 * 
	 * @return Connection session.
	 */
	public final String getConnectionSession() {
		String session = null;

		if (getRequest() != null) {
			session = getRequest().getParameter(
					TConnectionSession.CONNECTION_SESSION_REQUEST);
		}

		return session;
	}

	/**
	 * Recognises module name by session.
	 * 
	 * @return module name.
	 */
	public String getModuleName() {
		return getConnectionSession();
	}

	/*private final static AbstractDAOMap<IModel> hibernateDAOMap = new AbstractDAOMap<IModel>() {
		@Override
		protected IDAO<IModel> createDAO() {
			return new HibernateDAO();
		}
	};*/

	protected synchronized IDAO<IModel> getDAO() {
		LOGGER.debug("Hibernate DAO module name: " + getModuleName());
		//return hibernateDAOMap.getDAO(getModuleName());
		return HibernateDAOMap.dao.getDAO(getModuleName());
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
		return getRequest().getRequestURL().toString();
	}

	private HttpServletRequest request;
	private HttpServletResponse response;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		setRequest(request);
		setResponse(response);

		super.service(request, response);
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
