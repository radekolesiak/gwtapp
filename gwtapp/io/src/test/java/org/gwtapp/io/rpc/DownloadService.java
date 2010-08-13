package org.gwtapp.io.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("download.rpc")
public interface DownloadService extends RemoteService {
	void dowloadText(String text);
}
