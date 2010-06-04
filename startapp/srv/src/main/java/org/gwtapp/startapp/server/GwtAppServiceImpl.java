package org.gwtapp.startapp.server;

import org.apache.log4j.Logger;
import org.gwtapp.core.shared.exception.RpcException;
import org.gwtapp.startapp.shared.api.GwtAppService;
import org.gwtapp.startapp.shared.data.user.register.UserRegisterModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GwtAppServiceImpl extends RemoteServiceServlet implements
		GwtAppService {

	private final static Logger log = Logger.getLogger(GwtAppServiceImpl.class);

	@Override
	public void register(UserRegisterModel user) throws RpcException {
		log.debug("Register GWT Servlet");
	}

	@Override
	public void feedback(String from, String feedback) throws RpcException {
		try {
			SendFeedback send = new SendFeedback();
			send.sendFeedback(from, feedback);
		} catch (Exception e) {
			log.error("", e);
			throw new RpcException();
		}
	}

}
