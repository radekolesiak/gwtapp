package org.gwtapp.ccalc.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.ccalc.client.data.book.BookImpl;


public class Utils {

	private final static Logger log = Logger.getLogger(Utils.class);

	public final static String CONTENT_TYPE = "text/plain";
	public final static String EXTENSION = "xcc";
	public final static String DEFAULT_NAME = "CCalc";

	public static String getFilename(Book book) {
		return book.getName().isEmpty() ? DEFAULT_NAME + "." + EXTENSION : book
				.getName()
				+ "." + EXTENSION;
	}

	public static Book decode(String str) {
		return decode(str.getBytes());
	}

	public static Book decode(byte[] array) {
		Book book = new BookImpl();
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new ByteArrayInputStream(array)));
			book = (Book) decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			log.error("", e);
			book = BookImpl.createDefault();
		}
		return book;
	}

	public static byte[] encode(Book book) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(output));
		encoder.writeObject(book);
		encoder.close();
		return output.toByteArray();
	}
}
