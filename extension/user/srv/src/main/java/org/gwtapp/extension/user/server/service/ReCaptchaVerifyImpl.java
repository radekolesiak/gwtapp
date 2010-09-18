package org.gwtapp.extension.user.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.gwtapp.extension.user.client.data.ReCaptcha;
import org.gwtapp.extension.user.server.stub.ReCaptchaVerify;

/*
 * http://code.google.com/apis/recaptcha/docs/verify.html
 */
public class ReCaptchaVerifyImpl implements ReCaptchaVerify {

	private final static Logger log = Logger
			.getLogger(ReCaptchaVerifyImpl.class);

	public final static String RECAPTCHA_URL = "http://www.google.com/recaptcha/api/verify";

	@Override
	public boolean verify(ReCaptcha reCaptcha, String privateKey,
			String remoteIP) {
		try {
			List<String> response = fetchUrl(getURL(reCaptcha, privateKey,
					remoteIP));
			log.debug(response);
			// TODO throw exception on not 'true' response
			if (response != null && response.size() == 1) {
				return response.get(0).equalsIgnoreCase("true");
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return false;
	}

	public HttpURLConnection getURL(ReCaptcha reCaptcha, String privateKey,
			String remoteIP) throws IOException {
		// http://www.java-samples.com/java/POST-toHTTPS-url-free-java-sample-program.htm
		HttpURLConnection connection = null;
		String s = "";
		s += "privatekey=" + privateKey;
		s += "&";
		s += "remoteip=" + remoteIP;
		s += "&";
		s += "challenge=" + reCaptcha.getChallenge();
		s += "&";
		s += "response=" + reCaptcha.getResponse();
		log.debug(s);
		URL url = new URL(RECAPTCHA_URL + "?" + s);
		connection = (HttpURLConnection) url.openConnection();
		return connection;
	}

	public List<String> fetchUrl(HttpURLConnection connection) {
		List<String> response = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.add(line);
			}
			reader.close();
		} catch (IOException e) {
			log.error("", e);
		}
		return response;
	}
}
