package org.gwtapp.io.server;

import java.io.IOException;

import org.gwtapp.io.rpc.DownloadService;

@SuppressWarnings("serial")
public class DownloadServiceImpl extends IODownloadHttpServlet implements
		DownloadService {

	@Override
	public void dowloadText(String text) {
		if (text != null) {
			getThreadLocalResponse().setContentType("text/plain");
			getThreadLocalResponse().setContentLength(text.length());
			getThreadLocalResponse().setHeader("Content-Disposition",
					"attachment; filename=\"" + "filename.txt" + "\"");
			try {
				getThreadLocalResponse().getOutputStream().print(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
