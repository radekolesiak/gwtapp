package com.cuprum.web.common.rpc;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.utils.DAO;
import com.cuprum.server.common.utils.IDAO;
import com.cuprum.web.common.client.data.TConnectionSession;
import com.google.gwt.user.server.rpc.RPCServletUtils;
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

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO: use spring framework to inject the below code
		proxy(request, response);
	}

	protected void proxy(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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

	@SuppressWarnings("unused")
	private void writeResponse(HttpServletRequest request,
			HttpServletResponse response, String responsePayload)
			throws IOException {
		boolean gzipEncode = RPCServletUtils.acceptsGzipEncoding(request)
				&& shouldCompressResponse(request, response, responsePayload);

		RPCServletUtils.writeResponse(getServletContext(), response,
				responsePayload, gzipEncode);
	}
}
