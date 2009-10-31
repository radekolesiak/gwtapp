package org.gwtapp.startapp.server;

import org.gwtapp.core.client.exception.RpcException;
import org.gwtapp.startapp.client.api.GwtAppService;
import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GwtAppServiceImpl extends RemoteServiceServlet implements
		GwtAppService {

	@Override
	public void register(UserRegisterModel user) throws RpcException {
	}

}
