package org.gwtapp.io.server;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IOUploadHttpServlet<T> extends HttpServlet {

	private static final long serialVersionUID = -5624358129917811749L;

	public static enum UploadType {
		SUCCESSFUL, FAILULE;
	}

	private final static Logger log = Logger
			.getLogger(IOUploadHttpServlet.class);

	public void doSuccess(HttpServletResponse response, T object) {
		onResponse(response, UploadType.SUCCESSFUL, object);
	}

	public void doFailure(HttpServletResponse response, RpcException object) {
		onResponse(response, UploadType.FAILULE, object);
	}

	protected void onResponse(HttpServletResponse response, UploadType type,
			Object object) {
		response.setContentType("text/html");
		StringBuffer buffer = new StringBuffer();
		try {
			switch (type) {
			case SUCCESSFUL:
				if (object instanceof IsSerializable) {
					buffer.append(IOServer.encode(IOServer
							.success((IsSerializable) object)));
				} else if (object instanceof Serializable) {
					buffer.append(IOServer.encode(IOServer
							.success((Serializable) object)));
				}
				break;
			case FAILULE:
				buffer.append(IOServer.encode(IOServer
						.failure((Throwable) object)));
				break;
			}
		} catch (Exception e) {
			log.error("", e);
		}
		try {
			response.getOutputStream().write(buffer.toString().getBytes());
		} catch (IOException e) {
			log.error("", e);
		}
	}
}