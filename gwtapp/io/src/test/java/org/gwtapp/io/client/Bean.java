package org.gwtapp.io.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Bean implements IsSerializable {
	
	private String a;
	private Long b;
	private int c;
	
	public Bean() {
	}
	
	public Bean(String a, Long b, int c) {
		setA(a);
		setB(b);
		setC(c);
	}
	
	public void setA(String a) {
		this.a = a;
	}
	public String getA() {
		return a;
	}
	public void setB(Long b) {
		this.b = b;
	}
	public Long getB() {
		return b;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getC() {
		return c;
	}
}
