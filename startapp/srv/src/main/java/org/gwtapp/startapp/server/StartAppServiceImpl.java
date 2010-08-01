package org.gwtapp.startapp.server;

import java.util.Random;

import org.apache.log4j.Logger;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.startapp.rpc.api.StartAppService;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class StartAppServiceImpl extends RemoteServiceServlet implements
		StartAppService {

	private final static Logger log = Logger
			.getLogger(StartAppServiceImpl.class);

	private final static Random R = new Random();

	@Override
	public Long register(UserRegisterModel user) throws RpcException {
		log.debug("Register GWT Servlet");
		if (user != null) {
			return (long) R.nextInt();
		} else {
			return null;
		}
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
