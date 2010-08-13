package org.gwtapp.io.client;

import org.gwtapp.core.client.AsyncCallbackAdapter;
import org.gwtapp.io.rpc.DownloadService;
import org.gwtapp.io.rpc.DownloadServiceAsync;
import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.TimeoutException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.RootPanel;

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

	@Test
	public void testUploadValidLong() {
		final Long value = 123L;
		FormPanel form = new FormPanel();
		form.setAction("/org.gwtapp.io.IOTest.JUnit/upload.rpc");
		form.setMethod(FormPanel.METHOD_POST);
		form.add(new Hidden("value", "" + value));
		RootPanel.get().add(form);
		form.addSubmitCompleteHandler(new IOSubmitCompleteHandler<Long>() {
			@Override
			public void onSuccessful(Long result) {
				finishTest();
				assertEquals(value, result);
			}

			@Override
			public void onFailure(Throwable e) {
			}
		});
		form.submit();
		delayTestFinish(500);
	}

	@Test
	public void testUploadEmptyLong() {
		FormPanel form = new FormPanel();
		form.setAction("/org.gwtapp.io.IOTest.JUnit/upload.rpc");
		form.setMethod(FormPanel.METHOD_POST);
		form.add(new Hidden("value", ""));
		RootPanel.get().add(form);
		form.addSubmitCompleteHandler(new IOSubmitCompleteHandler<Long>() {
			@Override
			public void onSuccessful(Long result) {
				finishTest();
				assertNull(result);
			}

			@Override
			public void onFailure(Throwable e) {
			}
		});
		form.submit();
		delayTestFinish(500);
	}

	@Test
	public void testUploadInvalidValidLong() {
		FormPanel form = new FormPanel();
		form.setAction("/org.gwtapp.io.IOTest.JUnit/upload.rpc");
		form.setMethod(FormPanel.METHOD_POST);
		form.add(new Hidden("value", "123xyz"));
		RootPanel.get().add(form);
		form.addSubmitCompleteHandler(new IOSubmitCompleteHandler<Long>() {
			@Override
			public void onSuccessful(Long result) {
			}

			@Override
			public void onFailure(Throwable e) {
				finishTest();
			}
		});
		form.submit();
		delayTestFinish(500);
	}
}
