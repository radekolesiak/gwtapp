package com.cuprum.web.common.rpc;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.utils.DAO;
import com.cuprum.server.common.utils.IDAO;
import com.cuprum.web.common.client.data.TConnectionSession;
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

	private final static HashMap<String, IDAO> daos = new HashMap<String, IDAO>();

	protected synchronized IDAO getDAO() {
		if (!daos.containsKey(getModuleName())
				|| !((IDAO) daos.get(getModuleName())).getApplicationContext()
						.isActive()) {
			LOGGER.info("Creating DAO for: " + getModuleName());
			IDAO dao = new DAO();
			dao.setupContext(getModuleName());
			daos.put(getModuleName(), dao);
		}
		return daos.get(getModuleName());
	}

	public final ApplicationContext getApplicationContext() {
		if (getDAO() != null) {
			return getDAO().getApplicationContext();
		} else {
			LOGGER.error("DAO not found for: " + getModuleName());
			return null;
		}
	}

	public final <T extends Model> T getBean(Class<T> c) {
		if (getDAO() != null) {
			return getDAO().getBean(c);
		} else {
			LOGGER.error("DAO not found for: " + getModuleName());
			return null;
		}
	}

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return getThreadLocalRequest().getRequestURL().toString();
	}
}
