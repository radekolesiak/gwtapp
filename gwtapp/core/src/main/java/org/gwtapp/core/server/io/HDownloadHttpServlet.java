package org.gwtapp.core.server.io;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class HDownloadHttpServlet extends HttpServlet {

	private static final long serialVersionUID = -5479713141432764292L;

	private final ThreadLocal<HttpServletRequest> perThreadRequest = new ThreadLocal<HttpServletRequest>();
	private final ThreadLocal<HttpServletResponse> perThreadResponse = new ThreadLocal<HttpServletResponse>();

	private static final String EXPECTED_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String DATA_FIELD = "data";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			perThreadRequest.set(request);
			perThreadResponse.set(response);
			processCall(readContent(getThreadLocalRequest(), true));
		} catch (Throwable e) {
			log("", e);
		} finally {
			perThreadRequest.set(null);
			perThreadResponse.set(null);
		}
	}

	private void checkContentType(HttpServletRequest request)
			throws ServletException {
		String contentType = request.getContentType();
		if (!(contentType != null && contentType.toLowerCase().startsWith(
				EXPECTED_CONTENT_TYPE))) {
			throw new ServletException("Content-Type was '"
					+ (contentType == null ? "(null)" : contentType)
					+ "'. Expected '" + EXPECTED_CONTENT_TYPE + "'.");
		}
	}

	protected String processCall(String payload) throws SerializationException {
		try {
			RPCRequest rpcRequest = RPC.decodeRequest(payload, this.getClass());
			return RPC.invokeAndEncodeResponse(this, rpcRequest.getMethod(),
					rpcRequest.getParameters(), rpcRequest
							.getSerializationPolicy());
		} catch (IncompatibleRemoteServiceException ex) {
			log(
					"An IncompatibleRemoteServiceException was thrown while processing this call.",
					ex);
			return RPC.encodeResponseForFailure(null, ex);
		}
	}

	protected String readContent(HttpServletRequest request,
			boolean checkHeaders) throws ServletException, IOException {
		int contentLength = request.getContentLength();
		if (contentLength == -1) {
			// Content length must be known.
			throw new ServletException("Content-Length must be specified");
		}
		if (checkHeaders) {
			checkContentType(request);
			// checkCharacterEncoding(request);
		}
		return getThreadLocalRequest().getParameter(DATA_FIELD);
	}

	protected final HttpServletRequest getThreadLocalRequest() {
		return perThreadRequest.get();
	}

	protected final HttpServletResponse getThreadLocalResponse() {
		return perThreadResponse.get();
	}
}
