package org.gwtapp.startapp.server;

import org.gwtapp.core.server.html.io.HDownloadHttpServlet;
import org.gwtapp.startapp.client.api.DownloadService;
import org.gwtapp.startapp.client.data.user.register.UserRegister;

@SuppressWarnings("serial")
public class DownloadServiceImpl extends HDownloadHttpServlet implements
		DownloadService {

	@Override
	public void download(UserRegister userRegister) {
		try {
			if (userRegister != null) {
				String content = new String(Utils.encode(userRegister));
				String fileName = Utils.getFilename();
				getThreadLocalResponse().setContentType(Utils.CONTENT_TYPE);
				getThreadLocalResponse().setContentLength(content.length());
				getThreadLocalResponse().setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
				getThreadLocalResponse().getOutputStream().print(content);
			}
		} catch (Exception e) {
			log("", e);
		}
	}

}
