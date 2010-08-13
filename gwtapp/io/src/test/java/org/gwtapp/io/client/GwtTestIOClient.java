package org.gwtapp.io.client;

import org.gwtapp.core.client.AsyncCallbackAdapter;
import org.gwtapp.io.rpc.DownloadService;
import org.gwtapp.io.rpc.DownloadServiceAsync;
import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.TimeoutException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class GwtTestIOClient extends IOTest {

	@Test
	public void testDecode() {
		assertEquals("a&b", IOClient.decode("a&b"));
		assertEquals("a&b", IOClient.decode("a&amp;b"));
	}

	@Test(expected = TimeoutException.class)
	public void testDownloadTextNoServiceUpdate1() {
		DownloadServiceAsync service = GWT.create(DownloadService.class);
		service.dowloadText("aaa", new AsyncCallbackAdapter<Void>());
	}

	@Test(expected = TimeoutException.class)
	public void testDownloadTextNoServiceUpdate2() {
		DownloadServiceAsync service = GWT.create(DownloadService.class);
		service.dowloadText("aaa", new AsyncCallbackAdapter<Void>());
	}

	@Test
	public void testDownloadText1() {
		DownloadServiceAsync service = GWT.create(DownloadService.class);
		IORpcRequestBuilder.updateService((ServiceDefTarget) service);
		service.dowloadText("aaa", new AsyncCallbackAdapter<Void>());
	}

	@Test
	public void testDownloadText2() {
		DownloadServiceAsync service = GWT.create(DownloadService.class);
		IORpcRequestBuilder.updateService((ServiceDefTarget) service);
		service.dowloadText("aaa", new AsyncCallbackAdapter<Void>());
	}
}
