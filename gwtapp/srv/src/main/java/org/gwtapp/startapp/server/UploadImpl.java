package org.gwtapp.startapp.server;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.gwtapp.core.client.exception.RpcException;
import org.gwtapp.core.server.html.io.HUploadHttpServlet;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;

@SuppressWarnings("serial")
public class UploadImpl extends HUploadHttpServlet<UserRegisterModel> {

	public final static int MAX_SIZE = 1024 * 1024;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload upload = new ServletFileUpload();
				upload.setSizeMax(MAX_SIZE);
				FileItemIterator it = upload.getItemIterator(request);
				if (it.hasNext()) {
					FileItemStream item = it.next();
					InputStream input = item.openStream();
					try {
						if (!item.isFormField()) {
							UserRegisterModel book = Utils.decode(IOUtils
									.toByteArray(input));
							doSuccess(response, book);
							return;
						}
					} finally {
						IOUtils.closeQuietly(input);
					}
				}
				doFailure(response, new RpcException());
			}
		} catch (Exception e) {
			doFailure(response, new RpcException());
		}
	}
}
