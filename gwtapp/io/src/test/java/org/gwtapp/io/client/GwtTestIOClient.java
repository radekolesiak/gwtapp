package org.gwtapp.io.client;

import org.gwtapp.core.client.AsyncCallbackAdapter;
import org.gwtapp.core.client.Value;
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
	public void testDownloadTextNoServiceUpdate() {
		DownloadServiceAsync service = GWT.create(DownloadService.class);
		service.dowloadText("aaa", new AsyncCallbackAdapter<Void>());
	}

	@Test
	public void testDownloadText() {
		DownloadServiceAsync service = GWT.create(DownloadService.class);
		//final Value<Boolean> handled = new Value<Boolean>(false);
		IORpcRequestBuilder.updateService((ServiceDefTarget) service);
		service.dowloadText("aaa", new AsyncCallbackAdapter<Void>());
		// delayTestFinish(500);
		// assertTrue(handled.get());
	}
}
