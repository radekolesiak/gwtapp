package org.gwtapp.ccalc.client.ui.page;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.template.client.Param;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class PageHeaderPanel extends TemplateFormPanel<PageHeaderPanel.Model> {

	public static class Model {
		public static enum State {
			NONE, ADD, REMOVE;
			public Model get() {
				return new Model(this);
			}
		}

		public State state = State.NONE;
		public String title = "";

		public Model() {
		};

		public Model(String title) {
			this.title = title;
		}

		public Model(State state) {
			this.state = state;
		}
	}

	private final WidgetHandler add = add("add", new WidgetHandler());
	private final WidgetHandler remove = add("remove", new WidgetHandler());
	private final WidgetHandler title = add("title", new WidgetHandler());

	private final boolean full;

	public PageHeaderPanel(boolean full) {
		super(CCalc.templateService.load("page/PageHeaderPanel.jsp"));
		this.full = full;
	}

	@Override
	public void onAddWidgets() {
		if (full) {
			addStyleName(title.getMessage("fullStyle"));
		}
		add.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				event.stopPropagation();
				setValue(Model.State.ADD.get(), true);
			}
		});
		remove.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				event.stopPropagation();
				setValue(Model.State.REMOVE.get(), true);
			}
		});
	}

	@Override
	public void setValue(Model value, boolean fireEvents) {
		if (isTemplated()) {
			if (value.state == Model.State.NONE) {
				title.getWidget().setText(
						title.getParamMessage("title", new Param("title",
								value.title)));
			}
		}
		super.setValue(value, fireEvents);
	}
}
