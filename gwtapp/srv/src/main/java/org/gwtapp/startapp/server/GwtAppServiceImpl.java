package org.gwtapp.startapp.server;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.startapp.rpc.api.GwtAppService;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GwtAppServiceImpl extends RemoteServiceServlet implements
		GwtAppService {

	@Override
	public void register(UserRegisterModel user) throws RpcException {
		System.out.println("Register GWT Servlet");
	}

	@Override
	public void feedback(String from, String feedback) throws RpcException {
		SendFeedback send = new SendFeedback();
		send.sendFeedback(from, feedback);
	}

}
