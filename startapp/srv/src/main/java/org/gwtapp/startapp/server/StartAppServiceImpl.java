package org.gwtapp.startapp.server;

import org.apache.log4j.Logger;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.startapp.rpc.api.GwtAppService;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class StartAppServiceImpl extends RemoteServiceServlet implements
		GwtAppService {

	private final static Logger log = Logger.getLogger(StartAppServiceImpl.class);

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
