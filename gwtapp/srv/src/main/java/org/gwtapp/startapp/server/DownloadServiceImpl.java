package org.gwtapp.startapp.server;

import org.apache.log4j.Logger;
import org.gwtapp.core.server.io.HDownloadHttpServlet;
import org.gwtapp.startapp.rpc.api.DownloadService;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

@SuppressWarnings("serial")
public class DownloadServiceImpl extends HDownloadHttpServlet implements
		DownloadService {

	private final static Logger log = Logger
			.getLogger(DownloadServiceImpl.class);

	@Override
	public void download(UserRegisterModel userRegister) {
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
			log.debug("", e);
		}
	}

}
