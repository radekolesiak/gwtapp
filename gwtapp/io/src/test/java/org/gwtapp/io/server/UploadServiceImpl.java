package org.gwtapp.io.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.gwtapp.core.rpc.exception.RpcException;

@SuppressWarnings("serial")
public class UploadServiceImpl extends IOUploadHttpServlet<Long> {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Long value = null;
			String param = request.getParameter("value");
			if (!StringUtils.isEmpty(param)) {
				value = Long.parseLong(param);
			}
			doSuccess(response, value);
		} catch (Exception e) {
			e.printStackTrace();
			doFailure(response, new RpcException());
		}
	}
}
