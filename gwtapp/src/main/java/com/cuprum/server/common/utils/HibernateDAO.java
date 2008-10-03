package com.cuprum.server.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cuprum.server.common.model.IModel;

public class HibernateDAO implements IDAO<IModel> {
	ClassPathXmlApplicationContext context = null;

	public AbstractRefreshableApplicationContext getApplicationContext() {
		return context;
	}

	@SuppressWarnings("unchecked")
	public <C extends IModel> C getBean(Class<C> c) {
		ApplicationContext context = getApplicationContext();
		if (context == null || c == null) {
			return null;
		} else {
			C model = (C) context.getBean(c.getName());
			model.setDAO(this);
			return model;
		}
	}

	public void setApplicationContext(
			final ClassPathXmlApplicationContext context) {
		this.context = context;
	}

	private String name = null;

	public String getName() {
		return name;
	}

	public void setupContext(String name) {
		setApplicationContext(new ClassPathXmlApplicationContext(
				"/com/cuprum/server/config/db/bean-hibernate-" + name
						+ ".cfg.xml"));
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
