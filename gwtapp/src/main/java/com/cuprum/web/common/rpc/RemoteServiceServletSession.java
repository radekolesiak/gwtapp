package com.cuprum.web.common.rpc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cuprum.server.common.model.IModel;
import com.cuprum.server.common.utils.DAOMap;
import com.cuprum.server.common.utils.HibernateDAO;
import com.cuprum.server.common.utils.IDAO;
import com.cuprum.server.common.utils.RemoteServiceDAO;
import com.cuprum.web.common.client.data.TConnectionSession;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * RemoteServiceServlet with session support.
 * 
 * @author Radek Olesiak
 * 
 */

public class RemoteServiceServletSession extends RemoteServiceServlet {
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

		if (getThreadLocalRequest() != null) {
			session = getThreadLocalRequest().getParameter(
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

	private final static DAOMap<IModel> hibernateDAOMap = new DAOMap<IModel>() {
		@Override
		protected IDAO<IModel> createDAO() {
			return new HibernateDAO();
		}
	};

	protected synchronized IDAO<IModel> getDAO() {
		return hibernateDAOMap.getDAO(getModuleName());
	}

	public final <T extends IModel> T getBean(Class<T> c) {
		if (getDAO() != null) {
			return getDAO().getBean(c);
		} else {
			LOGGER.error("Hibernate DAO not found for: " + getModuleName());
			return null;
		}
	}

	private final static DAOMap<RemoteService> remoteServiceDAOMap = new DAOMap<RemoteService>() {
		@Override
		protected IDAO<RemoteService> createDAO() {
			return new RemoteServiceDAO();
		}
	};

	protected synchronized IDAO<RemoteService> getRemoteServiceDAO() {
		return remoteServiceDAOMap.getDAO(getModuleName());
	}

	public final <T extends RemoteService> T getRpcBean(Class<T> c) {
		if (getRemoteServiceDAO() != null) {
			return getRemoteServiceDAO().getBean(c);
		} else {
			LOGGER.error("RemoteService DAO not found for: " + getModuleName());
			return null;
		}
	}

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return getThreadLocalRequest().getRequestURL().toString();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpServlet servlet = null;

		if (this instanceof RemoteService) {
			try {
				RemoteService bean = getRpcBean(((RemoteService) this)
						.getClass());
				if (bean != null && bean instanceof HttpServlet) {
					servlet = (HttpServlet) bean;
					servlet.service(request, response);
				}
			} catch (Throwable e) {
				LOGGER.error(e);
			}
		}

		if (servlet == null) {
			super.service(request, response);
		}
	}
}
