package com.cuprum.server.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractDAO<T> implements IDAO<T> {
	ClassPathXmlApplicationContext context = null;

	public AbstractRefreshableApplicationContext getApplicationContext() {
		return context;
	}

	@SuppressWarnings("unchecked")
	public <C extends T> C getBean(Class<C> c) {
		ApplicationContext context = getApplicationContext();
		if (context == null || c == null) {
			return null;
		} else {
			return (C) context.getBean(c.getName());
		}
	}

	public void setApplicationContext(
			final ClassPathXmlApplicationContext context) {
		this.context = context;
	}

	private String name = null;

	protected String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	protected abstract String getClassPath();

	public void setupContext(String name) {
		setName(name);
		setApplicationContext(new ClassPathXmlApplicationContext(getClassPath()));
	}

	public void setupContext(HibernateDatabases database) {
		setupContext(database.getDatabase());
	}

	public void close() {
		ClassPathXmlApplicationContext ctx = ((ClassPathXmlApplicationContext) getApplicationContext());
		if (ctx != null) {
			ctx.stop();
			ctx.close();
			ctx.destroy();
		}
	}
}
